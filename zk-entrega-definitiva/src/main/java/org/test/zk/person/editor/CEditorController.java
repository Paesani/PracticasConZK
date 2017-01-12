package org.test.zk.person.editor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.test.zk.dao.TBLPersonDAO;
import org.test.zk.database.CDatabaseConnection;
import org.test.zk.datamodel.TBLPerson;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import commonlibs.commonclasses.CLanguage;
import commonlibs.commonclasses.ConstantsCommonClasses;
import commonlibs.extendedlogger.CExtendedLogger;

public class CEditorController extends SelectorComposer<Component> {

    /**
     * 
     */
    private static final long serialVersionUID = -8977563222707532143L;
    @Wire
    Window windowperson;
    @Wire
    Label labelci;
    @Wire
    Textbox textboxci;
    @Wire
    Label labelnombre;
    @Wire
    Textbox textboxnombre;
    @Wire
    Label labelapellido;
    @Wire
    Textbox textboxapellido;
    @Wire
    Label labeltelefono;
    @Wire
    Textbox textboxtelefono;
    @Wire
    Label labelfecha;
    @Wire
    Datebox dateboxfecha;
    @Wire
    Label labelgenero;
    @Wire
    Selectbox selectboxgenero;
    @Wire
    Label labelcomentario;
    @Wire
    Textbox textboxcomentario;
    @Wire
    Button buttonguardar;
    @Wire
    Button buttoncancelar;
    protected CExtendedLogger controllerLogger=null;
    protected CLanguage controllerLanguage=null;
    protected ListModelList<String> datamodel = new ListModelList<String>();
    protected Button buttonadd;
    protected Button buttonmodify;
    protected Execution execution = Executions.getCurrent();
    TBLPerson personToModify = (TBLPerson) execution.getArg().get("personToModify");
    protected CDatabaseConnection database = null;
    public static final String dbkey = "database";
    public void doAfterCompose(Component comp) {
        try {
            super.doAfterCompose(comp);
            controllerLogger = (CExtendedLogger) Sessions.getCurrent().getWebApp().getAttribute(ConstantsCommonClasses._Webapp_Logger_App_Attribute_Key);
            dateboxfecha.setFormat("dd-MM-yyyy");
            datamodel.add("Femenino");
            datamodel.add("Masculino");
            selectboxgenero.setModel(datamodel);
            selectboxgenero.setSelectedIndex(0);
            datamodel.addSelection("Femenino");
            Session sesion = Sessions.getCurrent();
            if(sesion.getAttribute(dbkey)instanceof CDatabaseConnection){
                database=(CDatabaseConnection) sesion.getAttribute(dbkey);
                if(execution.getArg().get("PersonaCi") instanceof String){
                    personToModify = TBLPersonDAO.loadData(database, (String) execution.getArg().get("PersonaCi"),controllerLogger,controllerLanguage);
                }
            }            
            //TBLPerson personToModify = (TBLPerson) execution.getArg().get("personToModify");
            buttonmodify = (Button) execution.getArg().get("buttonmodify");// TypeCast
            buttonadd = (Button) execution.getArg().get("buttonadd");// TypeCast
            textboxci.setValue(personToModify.getStrci());
            textboxnombre.setValue(personToModify.getnombre());
            textboxapellido.setValue(personToModify.getapellido());
            textboxtelefono.setValue(personToModify.gettelefono());
            if (personToModify.getGender() == 0) {
                datamodel.addToSelection("Femenino");
            } else {
                datamodel.addToSelection("Masculino");
            }
            if(personToModify.getCumple()!=null){
            dateboxfecha.setValue(java.sql.Date.valueOf(personToModify.getCumple()));
            }           
            textboxcomentario.setValue(personToModify.getComment());            
        } catch (Exception e) {
            if ( controllerLogger != null ){   
                controllerLogger.logException( "-1021", e.getMessage(), e );        
            
        }
        }
    }

    @Listen("onClick=#buttonguardar")
    public void onClickButtonGuardar(Event event) {
        /*
         * Messagebox.show( "CI:" + textboxci.getValue() + " Nombre:" +
         * textboxnombre.getValue() + " Apellido:" + textboxapellido.getValue()
         * + " Tel�fono:" + textboxtelefono.getValue() + " Fecha:" +
         * dateboxfecha.getValue() + " Comentario:" +
         * textboxcomentario.getValue(), "Aceptar", Messagebox.OK,
         * Messagebox.INFORMATION); //;//
         */
        if(dateboxfecha.getValue()!=null){
        LocalDate id = new java.sql.Date(dateboxfecha.getValue().getTime()).toLocalDate();
        personToModify.setci(textboxci.getValue());
        personToModify.setnombre(textboxnombre.getValue());
        personToModify.setapellido(textboxapellido.getValue());
        personToModify.settelefono(textboxtelefono.getValue());
        personToModify.setGender(selectboxgenero.getSelectedIndex());
        personToModify.setCumple(id);
        personToModify.setComment(textboxcomentario.getValue());
        if ((!personToModify.getStrci().equals("")) && (!personToModify.getnombre().equals("")) && (!personToModify.getapellido().equals("")) && !personToModify.gettelefono().equals("") && personToModify.getGender()>=0 && personToModify.getCumple()!=null && !personToModify.getComment().equals("")){            
        Events.echoEvent(new Event("onKek", buttonmodify, personToModify));
        windowperson.detach();                
        }else{
            Messagebox.show("       Error, uno de los campos est� vac�o", "Aceptar", Messagebox.OK, Messagebox.EXCLAMATION);
        }
        }else{
            Messagebox.show("       Error, fecha vac�a", "Aceptar", Messagebox.OK, Messagebox.EXCLAMATION);
        }
    }    

    @Listen("onClick=#buttoncancelar")
    public void onClickButtonCancelar(Event event) {
        Messagebox.show("       Acci�n Cancelada", "Aceptar", Messagebox.OK, Messagebox.EXCLAMATION);
        windowperson.detach();
    }
}
