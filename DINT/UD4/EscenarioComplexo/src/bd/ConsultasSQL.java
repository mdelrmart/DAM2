/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import clases.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAM 2
 */
public class ConsultasSQL {

    //Insertar raza
    public static int insertarRaza(Raza c) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "insert into razas (descripcion) VALUES ('" + c.getDescripcion() + "')";
            //System.out.println(consulta);
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    // Recuperar todas las razas
    public static Vector recuperarTodalasRazas() {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select codRaza, descripcion from razas order by descripcion";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                Raza c = new Raza(rs.getInt(1), rs.getString(2));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Recuperar a ultima dose aplicada dunha vacuna a un can
    public static int recuperarUltimaDoseDunhaVacinaParaOCan(String chip, int codVacina) {
        try {
            int resultado = 0;
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select max(dose)from vacinacions where chip='" + chip + "' and codVacina=" + codVacina;
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                resultado = rs.getInt(1);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Recuperar todas las vacunas
    public static Vector recuperarTodalasVacinas() {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select codVacina, nomeVacina, numTotalDoses from vacinas order by nomeVacina";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                Vacina c = new Vacina(rs.getInt(1), rs.getString(2), rs.getInt(3));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Recuperar todos los propietarios
    public static Vector recuperarTodolosPropietarios() {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select dni, nome, ap1, ap2, tlf, email from propietarios order by nome";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                Propietario c = new Propietario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Insertar perro
    public static int insertarCan(Can c) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "insert into cans (chip, nome, codRaza, dniPropietario) VALUES ('" + c.getChip() + "','" + c.getNombre() + "'," + c.getCodRaza() + ",'" + c.getDniPropietario() + "')";
            System.out.println(consulta);
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    //Insertar propietario
    public static int insertarPropietario(Propietario c) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "insert into propietarios (dni, nome, ap1, ap2, tlf, email) VALUES ('" + c.getDni() + "','" + c.getNome() + "','" + c.getAp1() + "','" + c.getAp2() + "','" + c.getTlf() + "','" + c.geteMail() + "')";
            //System.out.println(consulta);
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    //Recuperar todos los perros de un propietario
    public static Vector recuperarTodolosCansDunPropietario(String dniPropietario) {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select chip, nome, codRaza from cans where dniPropietario='" + dniPropietario + "' order by nome";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                Can c = new Can(rs.getString(1), rs.getString(2), rs.getInt(3), dniPropietario);
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Recuperar todas las vacunaciones de un perro
    public static Vector recuperarTodalasVacinacionsDunCan(String chip) {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select codVacinacion, codVacina, data, observacions, dose from vacinacions where chip='" + chip + "' order by codVacinacion";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                Vacinacion c = new Vacinacion(rs.getInt(1), chip, rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Recuperar una vacuna dado su codigo
    public static Vector recuperarUnhaVacunaDadoSeuCodigo(int codVacuna) {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select nomeVacina, numTotalDoses from vacinas where codVacina=" + codVacuna;
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                Vacina c = new Vacina(codVacuna, rs.getString(1), rs.getInt(2));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Comprobar si un DNI existe
    public static int existeDNI(String DNI) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select dni from propietarios where dni='" + DNI + "'";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                return 1;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //Comprobar si un chip existe
    public static int existeChip(String chip) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select chip from cans where chip='" + chip + "'";
            System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                return 1;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //Eliminar una vacunacion dado su codigo
    public static int eliminarUnhaVacinacionDadoSeuCodigo(int codVacinacion) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "delete from vacinacions where codVacinacion=" + codVacinacion;
            //System.out.println(consulta);
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public static int insertarVacunacion(Vacinacion c) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "insert into vacinacions (chip, codVacina, data, observacions, dose) values ('" + c.getChip() + "'," + c.getCodVacina() + ",'" + c.getData() + "','" + c.getObservacions() + "'," + c.getDose() + ")";
            System.out.println(consulta);
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    //Comprobar si existe una vacinacion de dose superior para un can
    public static int existeVacinacionDeDoseSuperior(int codVacina, int dose, String chip) {
        try {
            int resultado = 0;
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select count(*) from vacinacions where codVacina=" + codVacina + " and dose>" + dose + " and chip='" + chip + "'";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                resultado = rs.getInt(1);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int insertarOperacion(Operacion c) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "insert into operacions (chip, descripcion, data, anestesia, raios, sangue, scaner) "
                    + "VALUES ('" + c.getCodCan() + "','" + c.getDescripcion() + "','" + c.getData() + "',"
                    + c.isAnestesia() + "," + c.isRaios() + "," + c.isSangue() + "," + c.isScaner() + ")";
            System.out.println(consulta);
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public static Vector recuperarTodalasCitasDeOperacions() {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "SELECT operacions.codOperacion, propietarios.nome, propietarios.ap1, "
                    + "propietarios.ap2, cans.nome, operacions.data, operacions.descripcion "
                    + "FROM operacions, cans, propietarios "
                    + "where operacions.chip=cans.chip and "
                    + "cans.dnipropietario=propietarios.dni "
                    + "order by operacions.data";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                ListadoOperacion c = new ListadoOperacion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector recuperarTodalasCitasDeOperacionsEntreDousDatas(String dataInicial, String dataFinal) {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "SELECT operacions.codOperacion, propietarios.nome, propietarios.ap1, "
                    + "propietarios.ap2, cans.nome, operacions.data, operacions.descripcion "
                    + "FROM operacions, cans, propietarios "
                    + "where operacions.chip=cans.chip and "
                    + "cans.dnipropietario=propietarios.dni "
                    + "and operacions.data>='" + dataInicial + "' and operacions.data<='" + dataFinal + "'"
                    + "order by operacions.data";
            System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                ListadoOperacion c = new ListadoOperacion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector recuperarTodalasCitasDeOperacionsDunPropietario(String dni) {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "SELECT operacions.codOperacion, propietarios.nome, propietarios.ap1, "
                    + "propietarios.ap2, cans.nome, operacions.data, operacions.descripcion "
                    + "FROM operacions, cans, propietarios "
                    + "where operacions.chip=cans.chip and "
                    + "cans.dnipropietario=propietarios.dni "
                    + "and propietarios.dni='" + dni
                    + "' order by operacions.data";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                ListadoOperacion c = new ListadoOperacion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector recuperarTodalasCitasDeOperacionsDunPropietarioEntreDousDatas(String dni, String dataInicial, String dataFinal) {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "SELECT operacions.codOperacion, propietarios.nome, propietarios.ap1, "
                    + "propietarios.ap2, cans.nome, operacions.data, operacions.descripcion "
                    + "FROM operacions, cans, propietarios "
                    + "where operacions.chip=cans.chip and "
                    + "cans.dnipropietario=propietarios.dni "
                    + " and propietarios.dni='" + dni + "'"
                    + " and operacions.data>='" + dataInicial + "' and operacions.data<='" + dataFinal + "'"
                    + " order by operacions.data";
            //System.out.println(consulta);
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                ListadoOperacion c = new ListadoOperacion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int eliminarUnhaCitaDeOperacionDadoSeuCodigo(int codOperacion) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "delete from operacions where codOperacion=" + codOperacion;
            //System.out.println(consulta);
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public static Vector recuperarUnhaCitaDeOperacionDadoOSeuCodigo(int codOperacion) {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "SELECT descripcion, data, anestesia, raios, sangue, scaner "
                    + "FROM operacions "
                    + "where codOperacion=" + codOperacion;
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                Operacion c = new Operacion(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int recuperarNumeroDeOperacionsDunhaData(String data) {
        try {
            int resultado = -1;
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "SELECT count(*) from operacions where data='" + data + "'";
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                resultado = rs.getInt(1);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector recuperarIdFacturas(String strWhere) {
        try {
            String sql = "SELECT * FROM tcabsfacturas WHERE " + strWhere;
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                resultado.addElement(rs.getInt("id_factura"));
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    //(C)RUD propietarios
    public static int eliminarUnPropietarioDadoSeuDni(String dni) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "delete from propietarios where dni='" + dni + "'";
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //Recuperar un propietario dado o seu DNI
    public static Propietario recuperarPropietario(String dniPropietario) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select * from propietarios where dni='" + dniPropietario + "'";
            ResultSet rs = stmt.executeQuery(consulta);
            rs.next();
            Propietario p = new Propietario(rs.getString("dni"), rs.getString("nome"), rs.getString("ap1"), rs.getString("ap2"), rs.getString("tlf"), rs.getString("eMail"));
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int actualizarPropietario(String dniAntiguo, Propietario c) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "UPDATE propietarios SET dni = '" + c.getDni() + "', nome = '" + c.getNome() + "', ap1 = '" + c.getAp1() + "', ap2 = '" + c.getAp2() + "', tlf = '" + c.getTlf() + "', eMail = '" + c.geteMail() + "' WHERE dni = '" + dniAntiguo + "'";
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Recuperar todas as citas modernizado
    public static List<Propietario> recuperarTodolosPropietariosList() {
        List<Propietario> resultado = new ArrayList<>();

        String consulta = "select dni, nome, ap1, ap2, tlf, email from propietarios order by nome";

        try {
            Connection conexion = Conexion.getConexion();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while (rs.next()) {
                Propietario propietario = new Propietario(
                        rs.getString("dni"),
                        rs.getString("nome"),
                        rs.getString("ap1"),
                        rs.getString("ap2"),
                        rs.getString("tlf"),
                        rs.getString("eMail")
                );
                resultado.add(propietario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public static int verificarRestriccionesDeEliminacionDePropietario(String dniPropietario) {
        // Ejemplo: Devuelve 0 si se puede eliminar, -1 si hay error, y otro valor si hay restricciones
        String sql = "SELECT COUNT(*) FROM cans WHERE dniPropietario = ?";
        try {
            Connection conexion = Conexion.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setString(1, dniPropietario);

            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return 1; // Hay restricciones
            }

            return 0; // Se puede eliminar

        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Error
        }
    }

}
