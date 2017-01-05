package org.test.zk.zksubsystem;

import java.io.File;
import java.util.List;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.DesktopCleanup;
import org.zkoss.zk.ui.util.DesktopInit;
import org.zkoss.zk.ui.util.ExecutionCleanup;
import org.zkoss.zk.ui.util.ExecutionInit;
import org.zkoss.zk.ui.util.SessionCleanup;
import org.zkoss.zk.ui.util.SessionInit;
import org.zkoss.zk.ui.util.WebAppCleanup;
import org.zkoss.zk.ui.util.WebAppInit;

import commonlibs.commonclasses.CLanguage;
import commonlibs.commonclasses.ConstantsCommonClasses;
import commonlibs.extendedlogger.CExtendedConfigLogger;
import commonlibs.extendedlogger.CExtendedLogger;

public class CZKSubsystemEvents implements DesktopInit, DesktopCleanup, SessionInit, SessionCleanup, WebAppInit, WebAppCleanup, ExecutionInit, ExecutionCleanup {

    @Override
    public void cleanup(Execution exec, Execution parent, List<Throwable> errs) throws Exception {
        System.out.println("Executions Cleanup");
        
    }

    @Override
    public void init(Execution exec, Execution parent) throws Exception {
        System.out.println("Executions Init");
        
    }

    @Override
    public void cleanup(WebApp webApp) throws Exception {
        System.out.println("Web App Cleanup");
        try {

            //Obtenermos el logger
            CExtendedLogger webAppLogger = CExtendedLogger.getLogger( ConstantsCommonClasses._Webapp_Logger_Name );

            if ( webAppLogger != null ) {//Si el logger no está vacío
                
                //Escribimos un mensajes al log
                webAppLogger.logMessage( "1" , CLanguage.translateIf( null, "Webapp ending now." ) );

                //Cerramos el log
                webAppLogger.flushAndClose();
                
            }

            //Eliminamos el atributo del webapp
            webApp.removeAttribute( ConstantsCommonClasses._Webapp_Logger_Name );
            
        }
        catch ( Exception ex ) {
            
            System.out.println( ex.getMessage() );
            
        }
    }

    @Override
    public void init(WebApp webApp) throws Exception {
        System.out.println("Web App Init");
        try {
            
            String strRunningPath = webApp.getRealPath( ConstantsCommonClasses._WEB_INF_Dir )+File.separator;

            //Se encarga de leer el archivo de configuración logger.config.xml
            CExtendedConfigLogger configLogger = new CExtendedConfigLogger(); //Esta clase viene de mis librerías que importamos en la estructura del proyecto

            //Le decimos la ruta del archivo WEB-INF/config/logger.config.xml
            String strConfigPath = strRunningPath + ConstantsCommonClasses._Config_Dir + ConstantsCommonClasses._Logger_Config_File_Name;

            if ( configLogger.loadConfig( strConfigPath, null, null ) ) { //Cargamos la configuración

                //Aquí creamos el logger como tal
                CExtendedLogger webAppLogger = CExtendedLogger.getLogger( ConstantsCommonClasses._Webapp_Logger_Name );

                if ( webAppLogger.getSetupSet() == false ) { //Preguntamos si todavía no esta configurado

                    //Aquí le decimos donde va a crear los archivos de log WEB-INF/logs/system
                    String strLogPath = strRunningPath + ConstantsCommonClasses._Logs_Dir + ConstantsCommonClasses._System_Dir;

                    //Configuramos el logger según los parámetros de el archivo logger.config.xml y la ruta para escribir los archivos de log
                    webAppLogger.setupLogger( configLogger.getInstanceID(), configLogger.getLogToScreen(), strLogPath, ConstantsCommonClasses._Webapp_Logger_File_Log, configLogger.getClassNameMethodName(), configLogger.getExactMatch(), configLogger.getLevel(), configLogger.getLogIP(), configLogger.getLogPort(), configLogger.getHTTPLogURL(), configLogger.getHTTPLogUser(), configLogger.getHTTPLogPassword(), configLogger.getProxyIP(), configLogger.getProxyPort(), configLogger.getProxyUser(), configLogger.getProxyPassword() );

                    //Guardamos el logger principal en un atributo del webapp
                    webApp.setAttribute( ConstantsCommonClasses._Webapp_Logger_App_Attribute_Key, webAppLogger );

                }

                //Aquí escribimos al log en un archivo en WEB-INF/logs/system/webapplogger.log
                //Fijense en la clase CLanguage es otra de mis clases, pero no le presten mucha atención todavía
                //Basta con decir que es una clase que permite escribir los mensajes del log en varios idiomas
                webAppLogger.logMessage( "1" , CLanguage.translateIf( null, "Webapp logger loaded and configured [%s].", ConstantsCommonClasses._Webapp_Logger_Name ) );
                
            }

        }
        catch ( Exception ex ) {
            
            System.out.println( ex.getMessage() ); //Aquí no queda más remedio que usar la salida estandar el sistem la consola, por que todavia no existe el logger
            
        }
    }

    @Override
    public void cleanup(Session sess) throws Exception {
        System.out.println("Session Cleanup");
        
    }

    @Override
    public void init(Session sess, Object request) throws Exception {
        System.out.println("Session Init");
        
    }

    @Override
    public void cleanup(Desktop desktop) throws Exception {
        System.out.println("Desktop Cleanup");
        
    }

    @Override
    public void init(Desktop desktop, Object request) throws Exception {
        System.out.println("Desktop Init");
        
    }

}
