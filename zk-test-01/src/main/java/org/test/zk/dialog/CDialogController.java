package org.test.zk.dialog;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
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
    public void doAfterCompose(Component comp) {
        try {
            super.doAfterCompose( comp );
            dateboxfecha.setFormat("dd-MM-yyyy");
            datamodel.add("Femenino");
            datamodel.add("Masculino");
            selectboxgenero.setModel(datamodel);
            selectboxgenero.setSelectedIndex(0); 
            datamodel.addSelection("Femenino");
        } catch (Exception e) {     
            e.printStackTrace();
        }
    }    
    @Listen("onClick=#buttonguardar")
    public void onClickButtonGuardar(Event event) {        
        Messagebox.show("CI:"+ textboxci.getValue()+ " Nombre:"+ textboxnombre.getValue()+ " Apellido:" + textboxapellido.getValue()+ " Teléfono:"+ textboxtelefono.getValue()+ " Fecha:" + dateboxfecha.getValue()+ " Comentario:"+ textboxcomentario.getValue(), "Aceptar", Messagebox.OK, Messagebox.INFORMATION);;
        windowperson.detach();
    }
    @Listen("onClick=#buttoncancelar")
    public void onClickButtonCancelar(Event event) {
        Messagebox.show("       Acción Cancelada", "Aceptar", Messagebox.OK, Messagebox.EXCLAMATION);
        windowperson.detach();
    }
}
