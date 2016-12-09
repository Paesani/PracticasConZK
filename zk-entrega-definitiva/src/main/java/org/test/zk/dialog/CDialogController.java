package org.test.zk.dialog;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.test.zk.dao.CPerson;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
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

public class CDialogController extends SelectorComposer<Component> {

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
    protected ListModelList<String> datamodel = new ListModelList<String>();
    protected Button buttonadd;
    protected Button buttonmodify;
    protected Execution execution = Executions.getCurrent();
    CPerson personaToModify = (CPerson) execution.getArg().get("personToModify");

    public void doAfterCompose(Component comp) {
        try {
            super.doAfterCompose(comp);
            dateboxfecha.setFormat("dd-MM-yyyy");
            datamodel.add("Femenino");
            datamodel.add("Masculino");
            selectboxgenero.setModel(datamodel);
            selectboxgenero.setSelectedIndex(0);
            datamodel.addSelection("Femenino");
            CPerson personToModify = (CPerson) execution.getArg().get("personToModify");
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
            e.printStackTrace();
        }
    }

    @Listen("onClick=#buttonguardar")
    public void onClickButtonGuardar(Event event) {
        /*
         * Messagebox.show( "CI:" + textboxci.getValue() + " Nombre:" +
         * textboxnombre.getValue() + " Apellido:" + textboxapellido.getValue()
         * + " Teléfono:" + textboxtelefono.getValue() + " Fecha:" +
         * dateboxfecha.getValue() + " Comentario:" +
         * textboxcomentario.getValue(), "Aceptar", Messagebox.OK,
         * Messagebox.INFORMATION); //;//
         */
        if(dateboxfecha.getValue()!=null){
        LocalDate id = new java.sql.Date(dateboxfecha.getValue().getTime()).toLocalDate();
        personaToModify.setci(textboxci.getValue());
        personaToModify.setnombre(textboxnombre.getValue());
        personaToModify.setapellido(textboxapellido.getValue());
        personaToModify.settelefono(textboxtelefono.getValue());
        personaToModify.setGender(selectboxgenero.getSelectedIndex());
        personaToModify.setCumple(id);
        personaToModify.setComment(textboxcomentario.getValue());
        if ((!personaToModify.getStrci().equals("")) && (!personaToModify.getnombre().equals("")) && (!personaToModify.getapellido().equals("")) && !personaToModify.gettelefono().equals("") && personaToModify.getGender()>=0 && personaToModify.getCumple()!=null && !personaToModify.getComment().equals("")){
        Events.echoEvent(new Event("onKek", buttonmodify, personaToModify));
        windowperson.detach();                
        }else{
            Messagebox.show("       Error, uno de los campos está vacío", "Aceptar", Messagebox.OK, Messagebox.EXCLAMATION);
        }
        }else{
            Messagebox.show("       Error, fecha vacía", "Aceptar", Messagebox.OK, Messagebox.EXCLAMATION);
        }
    }    

    @Listen("onClick=#buttoncancelar")
    public void onClickButtonCancelar(Event event) {
        Messagebox.show("       Acción Cancelada", "Aceptar", Messagebox.OK, Messagebox.EXCLAMATION);
        windowperson.detach();
    }
}
