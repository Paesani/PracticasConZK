package org.test.zk.dao;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.test.zk.database.CDatabaseConnection;
import org.test.zk.datamodel.TBLPerson;

public class TBLPersonDAO {
    public static TBLPerson loadData(final CDatabaseConnection databaseConnection, final String CI) {
        TBLPerson resultado = null;
        try {
            if (databaseConnection != null && databaseConnection.getDbConection() != null) {
                Statement statement = databaseConnection.getDbConection().createStatement();
                ResultSet rs = statement.executeQuery("Select * from tblpersona Where Ci='"+CI+"'");
                if (rs.next()) {
                    resultado = new TBLPerson();
                    resultado.setci(rs.getString("Ci"));
                    resultado.setnombre(rs.getString("Nombre"));
                    resultado.setapellido(rs.getString("Apellido"));
                    resultado.settelefono(rs.getString("Telefono"));
                    resultado.setGender(rs.getInt("Genero"));
                    resultado.setCumple(rs.getDate("Cumple").toLocalDate());
                    resultado.setComment((rs.getString("Comentario")));
                    resultado.setCreadoPor(rs.getString("CreadoPor"));
                    resultado.setCreadoFecha(rs.getDate("CreadoFecha").toLocalDate());
                    resultado.setCreadoHora(rs.getTime("CreadoHora").toLocalTime());
                    resultado.setActualizadoPor(rs.getString("ActualizadoPor"));
                    resultado.setActualizadoFecha(rs.getDate("ActualizadoFecha") != null? rs.getDate("ActualizadoFecha").toLocalDate() : null);
                    resultado.setActualizadoHora(rs.getTime("ActualizadoHora") != null ? rs.getTime("ActualizadoHora").toLocalTime() : null);
                    //resultado=tblperson;
                }
                rs.close();
                statement.close();
                
                // NO SE CIERRA LA CONEXIÓN
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static boolean deleteData(final CDatabaseConnection databaseConnection, final String CI) {
        boolean bresultado = false;
        final String sqlQuerry = "Delete tlbpersona Where Ci = '"+CI+"'";
        try{
            if (databaseConnection != null && databaseConnection.getDbConection() != null) {
                Statement statement = databaseConnection.getDbConection().createStatement();
                statement.executeUpdate(sqlQuerry);
                bresultado=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return bresultado;
    }

    public static boolean insertData(final CDatabaseConnection databaseConnection, final TBLPerson tblperson) {
        boolean bresultado = false;
        final String sqlQuerry = "'"+tblperson.getStrci() + "','" + tblperson.getnombre() + "','" + tblperson.getapellido()
                + "','" + tblperson.gettelefono() + "','" + tblperson.getGender() + "','" + tblperson.getCumple() + "','"
                + tblperson.getComment() + "','root','" + LocalDate.now().toString() + "','" + LocalTime.now().toString()
                + "',null,null,null";
        try {
            if (databaseConnection != null && databaseConnection.getDbConection() != null) {
                Statement statement = databaseConnection.getDbConection().createStatement();
                statement.executeUpdate(
                        "Insert Into tblpersona(Ci,Nombre,Apellido,Telefono,Genero,Cumple,Comentario,CreadoPor,CreadoFecha,CreadoHora,ActualizadoPor,ActualizadoFecha,ActualizadoHora) Values("
                        + sqlQuerry + ")");
                databaseConnection.getDbConection().commit();
                bresultado=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bresultado;
    }

    public static boolean updateData(final CDatabaseConnection databaseConnection, final TBLPerson tblperson) {
        boolean bresultado = false;
        return bresultado;
    }

    public static List<TBLPerson> searchData(final CDatabaseConnection databaseConnection) {
        List<TBLPerson> resultado = new ArrayList<TBLPerson>();
        try {
            if (databaseConnection != null && databaseConnection.getDbConection() != null) {
                Statement statement = databaseConnection.getDbConection().createStatement();
                ResultSet rs = statement.executeQuery("Select * from tblpersona");
                while (rs.next()) {
                    TBLPerson tblperson = new TBLPerson();
                    tblperson.setci(rs.getString("Ci"));
                    tblperson.setnombre(rs.getString("Nombre"));
                    tblperson.setapellido(rs.getString("Apellido"));
                    tblperson.settelefono(rs.getString("Telefono"));
                    tblperson.setGender(rs.getInt("Genero"));
                    tblperson.setCumple(rs.getDate("Cumple").toLocalDate());
                    tblperson.setComment((rs.getString("Comentario")));
                    tblperson.setCreadoPor(rs.getString("CreadoPor"));
                    tblperson.setCreadoFecha(rs.getDate("CreadoFecha").toLocalDate());
                    tblperson.setCreadoHora(rs.getTime("CreadoHora").toLocalTime());
                    tblperson.setActualizadoPor(rs.getString("ActualizadoPor"));
                    tblperson.setActualizadoFecha(rs.getDate("ActualizadoFecha") != null
                            ? rs.getDate("ActualizadoFecha").toLocalDate() : null);
                    tblperson.setActualizadoHora(
                            rs.getTime("ActualizadoHora") != null ? rs.getTime("ActualizadoHora").toLocalTime() : null);
                    resultado.add(tblperson);
                }
                rs.close();
                statement.close();
                
                // NO SE CIERRA LA CONEXIÓN
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
