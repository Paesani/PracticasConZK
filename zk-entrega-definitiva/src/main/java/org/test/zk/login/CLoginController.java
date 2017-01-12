package org.test.zk.login;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.test.zk.constants.CConstantes;
import org.test.zk.dao.OperatorDAO;
import org.test.zk.database.CDatabaseConnection;
import org.test.zk.database.CDatabaseConnectionConfig;
import org.test.zk.datamodel.TBLOperator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import commonlibs.commonclasses.CLanguage;
import commonlibs.commonclasses.ConstantsCommonClasses;
import commonlibs.extendedlogger.CExtendedLogger;
import commonlibs.utils.Utilities;
import commonlibs.utils.ZKUtilities;
public class CLoginController extends SelectorComposer<Component> {

    /**
     * 
     */
    private static final long serialVersionUID = -2801680634365125022L;
    protected CExtendedLogger controllerLogger = null;
    
    protected CLanguage controllerLanguage = null;
    
    @Wire
    Textbox textboxOperator;

    @Wire
    Textbox textboxPassword;
    
    @Wire
    Label labelMessage;
    
    @Override
    public void doAfterCompose( Component comp ) {
        
        try {
         
            super.doAfterCompose( comp );
            
            //Obtenemos el logger del objeto webApp y guardamos una referencia en la variable de clase controllerLogger
            controllerLogger = (CExtendedLogger) Sessions.getCurrent().getWebApp().getAttribute( ConstantsCommonClasses._Webapp_Logger_App_Attribute_Key );
    
        }
        catch ( Exception ex ) {
            
            if ( controllerLogger != null )   
                controllerLogger.logException( "-1021", ex.getMessage(), ex );        
            
        }
    
    
    }    
    
    @Listen( "onChanging=#textboxOperator; onChanging=#textboxPassword" )
    public void onChangeTextbox( Event event ) {

        //A si distinguimos cual componente en pantalla lanzo el evento a veces es útil para hacer una cosa u otra en el método
        if ( event.getTarget().equals( textboxOperator ) ) {
            
            System.out.println( "Textbox operator" );
            
        }
        else if ( event.getTarget().equals( textboxPassword ) ) {
            
            System.out.println( "Textbox password" );
            
        }
        
        labelMessage.setValue( "" ); //Cuando cambie el texto en cualquiera de los dos textbox quitamos el mensaje. de error.
        
    }    
    
    @Listen( "onClick=#buttonLogin" )
    public void onClickButtonLogin( Event event ) {

        try {

            //Aquí vamos a conectarnos a la bd y vamos a verificar si los valores introducidos son válidos
            
            final String strOperator = ZKUtilities.getTextBoxValue( textboxOperator, controllerLogger );
            final String strPassword = ZKUtilities.getTextBoxValue( textboxPassword, controllerLogger );
            
            if ( strOperator.isEmpty() == false && strPassword.isEmpty() == false ) {//Si ambos campos no están vacíos
                
                CDatabaseConnection databaseConnection = new CDatabaseConnection();//Se instancia una conexion
                
                CDatabaseConnectionConfig databaseConnectionConfig = new CDatabaseConnectionConfig();//Se instancia una config conexion
                
                //En esta línea obtenemos la ruta completa del archivo de configuración incluido el /config/
                String strRunningPath = Sessions.getCurrent().getWebApp().getRealPath( ConstantsCommonClasses._WEB_INF_Dir ) + File.separator;
                
                if ( databaseConnectionConfig.loadConfig( strRunningPath + ConstantsCommonClasses._Config_Dir + ConstantsCommonClasses._Database_Connection_Config_File_Name, controllerLogger, controllerLanguage ) ) {
                    
                    if ( databaseConnection.makeConnectionToDB( databaseConnectionConfig, controllerLogger, controllerLanguage ) ) {

                        TBLOperator tblOperator = OperatorDAO.checkValid( databaseConnection, strOperator, strPassword, controllerLogger, controllerLanguage );
                        
                        if ( tblOperator != null ) {
                            
                            //Si es válido guardamos la conexión creada en la sesioón y guardamos la identidad del operador en la sesión
                         
                            //Damos un mensaje de bienvenida
                            labelMessage.setSclass( "" ); //Por defecto esta etiqueta usar el color rojo en la fuente por el estilo warn
                            labelMessage.setValue( "Welcome " + tblOperator.getName() + "!" ); //Ahora es color negro
                            
                            Session currentSession = Sessions.getCurrent();
                            
                            //Salvamos la conexión a la sesión actual del usuario, cada usuario/ pestaña tiene su sesión
                            currentSession.setAttribute( ConstantsCommonClasses._DB_Connection_Session_Key, databaseConnection ); //La sesion no es más que un arreglo asociativo
                            
                            //Salvamos la entidad del operador en la sesion
                            currentSession.setAttribute( CConstantes._Operator_Credential_Session_Key, tblOperator );
                            
                            controllerLogger.logMessage( "1" , CLanguage.translateIf( controllerLanguage, "Saved user credential in session for user [%s]", tblOperator.getName() ) );

                            //Obtenemos la fecha y la hora en el formato yyyy-MM-dd-HH-mm-ss
                            String strDateTime = Utilities.getDateInFormat( ConstantsCommonClasses._Global_Date_Time_Format_File_System_24, null );
                            
                            //Creamos la variable del logpath
                            String strLogPath = strRunningPath + CConstantes._Logs_Dir + strOperator + File.separator + strDateTime + File.separator;
                            
                            //La guardamos en la sesion
                            currentSession.setAttribute( CConstantes._Log_Path_Session_Key, strLogPath );

                            controllerLogger.logMessage( "1" , CLanguage.translateIf( controllerLanguage, "Saved user log path [%s] in session for user [%s]", strLogPath, strOperator ) );
                            
                            //Guardamos la fecha y la hora del inicio de sesión
                            currentSession.setAttribute( CConstantes._Login_Date_Time_Session_Key, strDateTime );
                            
                            controllerLogger.logMessage( "1" , CLanguage.translateIf( controllerLanguage, "Saved user login date time [%s] in session for user [%s]", strDateTime, strOperator ) );
                            
                            //Creamos la lista de logger de esta sesion
                            List<String> loggedSessionLoggers = new LinkedList<String>();
                            
                            //Guardamos la lista vacia en la sesion
                            currentSession.setAttribute( CConstantes._Logged_Session_Loggers, loggedSessionLoggers );
                            
                            //Actualizamos en bd el último inicio de sesión
                            OperatorDAO.updateLastLogin( databaseConnection, tblOperator.getId(), controllerLogger, controllerLanguage );                            
                            
                            //Redirecionamos hacia el home.zul
                            Executions.sendRedirect( "/views/home/home.zul" ); 
                            
                        }
                        else {
                            
                            labelMessage.setValue( "Invalid operator name and/or password!" );
                            
                            //Messagebox.show( "Invalid operator name and/or password" );
                            
                        }
                        
                    }
                    else {
                        
                        Messagebox.show( "Database connection failed" );
                        
                    }
                    
                }
                else {
                    
                    Messagebox.show( "Error to read the database config file" );
                    
                }
                
            }
            
        }
        catch ( Exception ex ) {
            
            if ( controllerLogger != null )   
                controllerLogger.logException( "-1021", ex.getMessage(), ex );        
            
        }
    
    }    
    
    @Listen("onTimer=#timerKeepAliveSession" )
    public void onTimer( Event event ) {
        
        //Este evento se ejecutara cada 120000 milisegundos 1 minuto aprox, esto no es un relog atomico a si que son tiempos aproximados
        //Muestra un tablerito en la parte superior central de la pantalla
        Clients.showNotification( "Automatic renewal of the session successful", "info",  null, "before_center", 2000, true );
        
    }
}
