/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import clases.*;
import java.util.ArrayList;
/**
 *
 * @author DAM IES Chan do Monte
 */
public class ConsultasSQL {
    
    
    //Insertar raza
    public static int insertarRaza(Raza c)
    {
        try
        {
            Statement stmt=Conexion.getConexion().createStatement();
            String consulta= "insert into razas (descripcion) VALUES ('"+c.getDescripcion()+"')";
            //System.out.println(consulta);
            stmt.executeUpdate(consulta);            
            return 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
        
    }
    
    
    // Recuperar todas las razas
    public static ArrayList recuperarTodalasRazas()
    {
        try
        {
            ArrayList resultado=new ArrayList();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta= "select codRaza, descripcion from razas order by descripcion";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while(rs.next())
            {
                Raza c=new Raza(rs.getInt(1), rs.getString(2));
                resultado.add(c);
            }
            return resultado;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    // Recuperar a ultima dose aplicada dunha vacuna a un can
    public static int recuperarUltimaDoseDunhaVacinaParaOCan(String chip, int codVacina)
    {
        try
        {
            int resultado=0;
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta= "select max(dose)from vacinacions where chip='"+chip+"' and codVacina="+codVacina;
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while(rs.next())
            {
                resultado=rs.getInt(1);
            }
            return resultado;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }    
    
    // Recuperar todas las vacunas
    public static ArrayList recuperarTodalasVacinas()
    {
        try
        {
            ArrayList resultado=new ArrayList();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta= "select codVacina, nomeVacina, numTotalDoses from vacinas order by nomeVacina";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while(rs.next())
            {
                Vacina c=new Vacina(rs.getInt(1), rs.getString(2), rs.getInt(3));
                resultado.add(c);
            }
            return resultado;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }    
    
    // Recuperar todos los propietarios
    public static ArrayList recuperarTodolosPropietarios()
    {
        try
        {
            ArrayList resultado=new ArrayList();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta= "select dni, nome, ap1, ap2, tlf, email from propietarios order by nome";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while(rs.next())
            {
                Propietario c=new Propietario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                resultado.add(c);
            }
            return resultado;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    //Inserir perro
    public static int inserirCan(Can c)
    {
        try
        {
            Statement stmt=Conexion.getConexion().createStatement();
            String consulta= "insert into cans (chip, nome, codRaza, dniPropietario) VALUES ('"+c.getChip()+"','"+c.getNombre()+"',"+c.getCodRaza()+",'"+c.getDniPropietario()+"')";
            System.out.println(consulta);
            stmt.executeUpdate(consulta);            
            return 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
        
    }
    
    //Insertar propietario
    public static int insertarPropietario(Propietario c)
    {
        try
        {
            Statement stmt=Conexion.getConexion().createStatement();
            String consulta= "insert into propietarios (dni, nome, ap1, ap2, tlf, email) VALUES ('"+c.getDni()+"','"+c.getNome()+"','"+c.getAp1()+"','"+c.getAp2()+"','"+c.getTlf()+"','"+c.geteMail()+"')";
            //System.out.println(consulta);
            stmt.executeUpdate(consulta);            
            return 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
        
    }    
    
    //Recuperar todos los perros de un propietario
    public static ArrayList recuperarTodolosCansDunPropietario(String dniPropietario)
    {
        try
        {
            ArrayList resultado=new ArrayList();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta= "select chip, nome, codRaza from cans where dniPropietario='"+dniPropietario+"' order by nome";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while(rs.next())
            {
                Can c=new Can(rs.getString(1), rs.getString(2), rs.getInt(3), dniPropietario);
                resultado.add(c);
            }
            return resultado;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    //Recuperar todas las vacunaciones de un perro
    public static ArrayList recuperarTodalasVacinacionsDunCan(String chip)
    {
        try
        {
            ArrayList resultado=new ArrayList();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta= "select codVacinacion, codVacina, data, observacions, dose from vacinacions where chip='"+chip+"' order by codVacinacion";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while(rs.next())
            {
                Vacinacion c=new Vacinacion(rs.getInt(1), chip, rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                resultado.add(c);
            }
            return resultado;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }    
    
    //Recuperar una vacuna dado su codigo
    public static ArrayList recuperarUnhaVacinaDadoSeuCodigo(int codVacuna)
    {
        try
        {
            ArrayList resultado=new ArrayList();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta= "select nomeVacina, numTotalDoses from vacinas where codVacina="+codVacuna;
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while(rs.next())
            {
                Vacina c=new Vacina(codVacuna, rs.getString(1), rs.getInt(2));
                resultado.add(c);
            }
            return resultado;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }  
    
    
    //Comprobar si un DNI existe
    public static int existeDNI(String DNI)
    {
        try
        {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta= "select dni from propietarios where dni='"+DNI+"'";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while(rs.next())
            {
                return 1;
            }
            return 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }      
    
    //Comprobar si un chip existe
    public static int existeChip(String chip)
    {
        try
        {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta= "select chip from cans where chip='"+chip+"'";
            System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while(rs.next())
            {
                return 1;
            }
            return 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }          
    

    //Eliminar una vacunacion dado su codigo
    public static int eliminarUnhaVacinacionDadoSeuCodigo(int codVacinacion)
    {
        try
        {
            Statement stmt=Conexion.getConexion().createStatement();
            String consulta= "delete from vacinacions where codVacinacion="+codVacinacion;
            //System.out.println(consulta);
            stmt.executeUpdate(consulta);            
            return 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
        
    }
    
    public static int inserirVacinacion(Vacinacion c)
    {
        try
        {
            Statement stmt=Conexion.getConexion().createStatement();
            String consulta= "insert into vacinacions (chip, codVacina, data, observacions, dose) values ('"+c.getChip()+"',"+c.getCodVacina()+",'"+c.getData()+"','"+c.getObservacions()+"',"+c.getDose()+")";
            System.out.println(consulta);
            stmt.executeUpdate(consulta);            
            return 0;
        } 
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
        
    }
    
    
    //Comprobar si existe una vacinacion de dose superior para un can
    public static int existeVacinacionDeDoseSuperior(int codVacina, int dose, String chip)
    {
        try
        {
            int resultado=0;
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta= "select count(*) from vacinacions where codVacina="+codVacina+" and dose>"+dose+" and chip='"+chip+"'";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while(rs.next())
            {
                resultado=rs.getInt(1);
            }
            return resultado;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }              

}


