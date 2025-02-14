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
 * @author DAM2
 */
public class ConsultasSQLPerrucaria {

    // Recuperar citas dunha data
    public static List<CitaPerrucaria> recuperarTodaAsCitasDunhaData(String data) {
        try {
            List<CitaPerrucaria> resultado = new ArrayList<>();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "select hora from citasperrucaria where data='" + data + "'";
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                CitaPerrucaria c = new CitaPerrucaria(rs.getInt(1));
                resultado.add(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Graba unha cita
    public static int inserirCitaPerrucaria(CitaPerrucaria c) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "insert into citasperrucaria (chip, data, hora) VALUES ('" + c.getCodCan() + "','" + c.getData() + "'," + c.getHora() + ")";
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    // Recuperar todas as citas
    public static Vector recuperarTodaAsCitasDePerrucaria() {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "SELECT citasperrucaria.codCita, propietarios.nome, propietarios.ap1, propietarios.ap2, cans.nome, citasperrucaria.data, "
                    + "citasperrucaria.hora FROM citasperrucaria, cans, propietarios "
                    + "where citasperrucaria.chip=cans.chip and cans.dnipropietario=propietarios.dni order by citasperrucaria.data";
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                ListadoPerrucaria c = new ListadoPerrucaria(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Recuperar todas as citas modernizado
    public static List<ListadoPerrucaria> recuperarTodaAsCitasDePerrucariaList() {
        List<ListadoPerrucaria> resultado = new ArrayList<>();

        String consulta = "SELECT citasperrucaria.codCita, propietarios.nome, propietarios.ap1, propietarios.ap2, "
                + "cans.nome, citasperrucaria.data, citasperrucaria.hora "
                + "FROM citasperrucaria "
                + "INNER JOIN cans ON citasperrucaria.chip = cans.chip "
                + "INNER JOIN propietarios ON cans.dnipropietario = propietarios.dni "
                + "ORDER BY citasperrucaria.data";

        try {
            Connection conexion = Conexion.getConexion();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while (rs.next()) {
                ListadoPerrucaria cita = new ListadoPerrucaria(
                        rs.getInt("codCita"),
                        rs.getString("nome"),
                        rs.getString("ap1"),
                        rs.getString("ap2"),
                        rs.getString("cans.nome"),
                        rs.getString("data"),
                        rs.getInt("hora")
                );
                resultado.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // Recuperar todas as citas filtrando por dni propietario
    public static Vector recuperarTodaAsCitasDePerrucariaDunPropietario(String dni) {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "SELECT citasperrucaria.codCita, propietarios.nome, propietarios.ap1, propietarios.ap2, cans.nome, citasperrucaria.data, "
                    + "citasperrucaria.hora FROM citasperrucaria, cans, propietarios "
                    + "where citasperrucaria.chip=cans.chip and cans.dnipropietario=propietarios.dni and "
                    + "propietarios.dni='" + dni + "' order by citasperrucaria.data";
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                ListadoPerrucaria c = new ListadoPerrucaria(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Recuperar todas as citas con filtro por datas
    public static Vector recuperarTodaAsCitasDePerrucariaEntreDuasDatas(String dataInicial, String dataFinal) {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "SELECT citasperrucaria.codCita, propietarios.nome, propietarios.ap1, propietarios.ap2, cans.nome, citasperrucaria.data, "
                    + "citasperrucaria.hora FROM citasperrucaria, cans, propietarios "
                    + "where citasperrucaria.chip=cans.chip and cans.dnipropietario=propietarios.dni "
                    + "and citasperrucaria.data>='" + dataInicial + "' and citasperrucaria.data<='" + dataFinal + "'"
                    + " order by citasperrucaria.data";
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                ListadoPerrucaria c = new ListadoPerrucaria(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Recuperar todas as citas con filtro por datas modernizado
    public static List<ListadoPerrucaria> recuperarTodaAsCitasDePerrucariaEntreDuasDatasList(String dataInicial, String dataFinal) {
        List<ListadoPerrucaria> resultado = new ArrayList<>();

        String consulta = "SELECT citasperrucaria.codCita, propietarios.nome, propietarios.ap1, propietarios.ap2, cans.nome, citasperrucaria.data, "
                + "citasperrucaria.hora FROM citasperrucaria, cans, propietarios "
                + "where citasperrucaria.chip=cans.chip and cans.dnipropietario=propietarios.dni "
                + "and citasperrucaria.data>='" + dataInicial + "' and citasperrucaria.data<='" + dataFinal + "'"
                + " order by citasperrucaria.data";

        try {
            Connection conexion = Conexion.getConexion();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while (rs.next()) {
                ListadoPerrucaria cita = new ListadoPerrucaria(
                        rs.getInt("codCita"),
                        rs.getString("nome"),
                        rs.getString("ap1"),
                        rs.getString("ap2"),
                        rs.getString("cans.nome"),
                        rs.getString("data"),
                        rs.getInt("hora")
                );
                resultado.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public static Vector recuperarTodaAsCitasDePerrucariaDunPropietarioEntreDuasDatas(String dni, String dataInicial, String dataFinal) {
        try {
            Vector resultado = new Vector();
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "SELECT citasperrucaria.codCita, propietarios.nome, propietarios.ap1, propietarios.ap2, cans.nome, citasperrucaria.data, "
                    + "citasperrucaria.hora FROM citasperrucaria, cans, propietarios "
                    + "where citasperrucaria.chip=cans.chip and cans.dnipropietario=propietarios.dni "
                    + " and propietarios.dni='" + dni + "'"
                    + " and citasperrucaria.data>='" + dataInicial + "' and citasperrucaria.data<='" + dataFinal + "'"
                    + " order by citasperrucaria.data";
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                ListadoPerrucaria c = new ListadoPerrucaria(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                resultado.addElement(c);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int eliminarUnhaCitaDadoSeuCodigo(int codCita) {
        try {
            Statement stmt = Conexion.getConexion().createStatement();
            String consulta = "delete from citasperrucaria where codCita=" + codCita;
            stmt.executeUpdate(consulta);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public static int verificarRestriccionesDeEliminacionDeCita(int codCita) {
        // Ejemplo: Devuelve 0 si se puede eliminar, -1 si hay error, y otro valor si hay restricciones
        String sql = "SELECT COUNT(*) FROM otras_tablas WHERE codCita = ?";
        try {
            Connection conexion = Conexion.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, codCita);
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
