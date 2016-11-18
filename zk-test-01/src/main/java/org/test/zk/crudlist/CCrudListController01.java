package org.test.zk.crudlist;

import org.test.zk.tarea.CTarea;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;
import org.test.zk.crud.*;

public class CCrudListController01 extends SelectorComposer<Component> {//Recuerda preguntar como hacer que
//una ventana pida datos de la otra
    /**
     * 
     */
    private static final long serialVersionUID = 9173328513665779362L;
    @Wire
    Button buttoncreate;
    @Wire
    Button buttondelete;
    @Wire
    Listbox box = null;
    public void doAfterCompose(Component comp) {
        try {
            super.doAfterCompose( comp );   
            
        } catch (Exception e) {     
            e.printStackTrace();
        }
    }
    @Listen("onClick=#buttoncreate")
    public void onClickButtonCreate(Event event) {
        CTarea NuevaTarea = new CTarea("", "");
        CCrudController01 crud = new CCrudController01();
        //NuevaTarea = crud.onClickbuttonnew(event);        
    }
}
