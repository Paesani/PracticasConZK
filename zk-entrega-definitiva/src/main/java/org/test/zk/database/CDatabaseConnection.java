package org.test.zk.database;

import java.io.Serializable;
import java.sql.*; 
public class CDatabaseConnection implements Serializable {
    
    private static final long serialVersionUID = -821612696326102519L;
    
    //protected final String db_url="jdbc:mysql://127.0.0.1:3306/prueba";    
    
    //protected final String user="root";
    
    //protected final String password="25639478";
    
    protected Connection dbConnection = null;
    
    public Connection getDbConection() {
        return dbConnection;
    }

    public void setDbConection(Connection dbConection) {
        this.dbConnection = dbConection;
    }

    public boolean makeConectionToDatabase(CDatabaseConnectionConfig databaseConnectionConfig){
        boolean resultado = false;
        try{            
            if(databaseConnectionConfig!=null){
                Class.forName(databaseConnectionConfig.getDriver());//Se inicializa el driver de mysql
                final String databaseurl = databaseConnectionConfig.getPrefix()+databaseConnectionConfig.getHost()+":"+databaseConnectionConfig.getPort()+"/"+databaseConnectionConfig.getDatabase();
                dbConnection=DriverManager.getConnection(databaseurl,databaseConnectionConfig.getUser(),databaseConnectionConfig.getPassword());//Se le asigna la base de datos con usuario y contraseña            
                dbConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                dbConnection.setAutoCommit(false);
                resultado=true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    public boolean CloseConnectionToDatabase(){
        boolean resultado = false;
        try{
            if(dbConnection!=null){
                dbConnection.close();
                dbConnection=null;
                resultado=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    
}
