package org.test.zk.dao;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.test.zk.database.CDatabaseConnection;
import org.test.zk.datamodel.TBLPerson;

public class TBLPersonDAO {
    public static TBLPerson loadData ( final CDatabaseConnection databaseConnection, final String CI){
         TBLPerson resultado = null;
         return resultado;
    }
    public static boolean deleteData(final CDatabaseConnection databaseConnection, final String CI){
        boolean bresultado = false;
        return bresultado;
    }
    public static boolean insertData(final CDatabaseConnection databaseConnection, final TBLPerson tblperson){
        boolean bresultado = false;
        return bresultado;
    }
    public static boolean updateData(final CDatabaseConnection databaseConnection, final TBLPerson tblperson){
        boolean bresultado = false;
        return bresultado;
    }
    public static List<TBLPerson> searchData(final CDatabaseConnection databaseConnection){
        List<TBLPerson> resultado = new ArrayList<TBLPerson>();
        try{
            if(databaseConnection!=null && databaseConnection.getDbConection()!=null){
                Statement statement=databaseConnection.getDbConection().createStatement();
                ResultSet rs = statement.executeQuery("Select * from tblpersona");
                while(rs.next()){
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
                    tblperson.setActualizadoFecha(rs.getDate("ActualizadoFecha")!=null ? rs.getDate("ActualizadoFecha").toLocalDate():null);
                    tblperson.setActualizadoHora(rs.getTime("ActualizadoHora")!=null ? rs.getTime("ActualizadoHora").toLocalTime():null);
                    //Si
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
}
