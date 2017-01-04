package org.test.zk.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Properties;

public class CDatabaseConnectionConfig implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -292714503181660487L;
    
    protected String strDriver = null;
    protected String strPrefix = null;
    protected String strHost = null;
    protected String strPort = null;
    protected String strDatabase = null;
    protected String strUser = null;
    protected String strPassword = null;
    
    public CDatabaseConnectionConfig(){
        
    }
    
    public CDatabaseConnectionConfig(String strDriver, String strPrefix, String strHost, String strPort,
            String strDatabase, String strUser, String strPassword) {
        super();
        this.strDriver = strDriver;
        this.strPrefix = strPrefix;
        this.strHost = strHost;
        this.strPort = strPort;
        this.strDatabase = strDatabase;
        this.strUser = strUser;
        this.strPassword = strPassword;
    }

    public String getDriver() {
        return strDriver;
    }

    public void setDriver(String strDriver) {
        this.strDriver = strDriver;
    }

    public String getPrefix() {
        return strPrefix;
    }

    public void setPrefix(String strPrefix) {
        this.strPrefix = strPrefix;
    }

    public String getHost() {
        return strHost;
    }

    public void setHost(String strHost) {
        this.strHost = strHost;
    }

    public String getPort() {
        return strPort;
    }

    public void setPort(String strPort) {
        this.strPort = strPort;
    }

    public String getDatabase() {
        return strDatabase;
    }

    public void setDatabase(String strDatabase) {
        this.strDatabase = strDatabase;
    }

    public String getUser() {
        return strUser;
    }

    public void setUser(String strUser) {
        this.strUser = strUser;
    }

    public String getPassword() {
        return strPassword;
    }

    public void setPassword(String strPassword) {
        this.strPassword = strPassword;
    }
    public boolean loadConfig(String ConfigPath){        
        boolean resultado = false;
        try{
            File configFilePath = new File(ConfigPath);//Se crea un apuntador con la dirección ConfigPath
            if(configFilePath.exists()){//Si existe esa dirección
                Properties ConfigData = new Properties();//Se crea una propiedad con la data de la configuración
                FileInputStream inputStream = new FileInputStream(configFilePath);
                ConfigData.loadFromXML(inputStream);//Aquí se lee el archivo
                this.strDriver= ConfigData.getProperty("driver");
                this.strPrefix= ConfigData.getProperty("prefix");
                this.strHost= ConfigData.getProperty("host");
                this.strPort= ConfigData.getProperty("port");
                this.strDatabase= ConfigData.getProperty("database");
                this.strUser= ConfigData.getProperty("user");
                this.strPassword= ConfigData.getProperty("password");
                inputStream.close();//Cerramos el Stream
                resultado=true;
            }else{
                System.out.println("Error, el archivo no fue encontrado");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
}
