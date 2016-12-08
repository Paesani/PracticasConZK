package org.test.zk.manager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.test.zk.dao.CPerson;
import org.test.zk.manager.CManagerController.MyRenderer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class CManagerController extends SelectorComposer<Component> {

    /**
     * 
     */
    private static final long serialVersionUID = -1591648938821366036L;
    protected ListModelList<CPerson> datamodelpersona = new ListModelList<CPerson>();
    @Wire
    Button buttonadd;
    @Wire
    Button buttonmodify;
    @Wire
    Button buttondelete;
    @Wire
    Listbox listboxpersons;
    @Wire
    Window windowmanager;
    protected Execution execution = Executions.getCurrent();
    public class MyRenderer implements ListitemRenderer<CPerson> {

        @Override
        public void render(Listitem listitem, CPerson persona, int arg2) throws Exception {
            try {

                Listcell cell = new Listcell();// Se crea una nueva celda
                cell.setLabel(persona.getStrci());// Se le asigna un valor a
                                                  // la// celda
                listitem.appendChild(cell);// Se añade la celda a la lista
                cell = new Listcell();// Se crea una nueva celda
                cell.setLabel(persona.getnombre());// Se le asigna un valor a
                                                   // la// celda
                listitem.appendChild(cell);// Se añade la celda a la lista
                cell = new Listcell();// Se crea una nueva celda
                cell.setLabel(persona.getapellido());// Se le asigna un valor a
                                                     // la// celda
                listitem.appendChild(cell);// Se añade la celda a la lista
                cell = new Listcell();// Se crea una nueva celda
                cell.setLabel(persona.gettelefono());// Se le asigna un valor a
                                                     // la celda
                listitem.appendChild(cell);// Se añade la celda a la lista                
                cell = new Listcell();// Se crea una nueva celda
                cell.setLabel(persona.getGender() == 0 ? "Femenino" : "Masculino");// Se le asigna un valor a
                                                     // la celda                
                listitem.appendChild(cell);// Se añade la celda a la lista
                cell = new Listcell();// Se crea una nueva celda
                cell.setLabel( persona.getCumple().toString());// Se le asigna un valor a
                                                     // la celda
                listitem.appendChild(cell);// Se añade la celda a la lista
                cell = new Listcell();// Se crea una nueva celda
                cell.setLabel(persona.getComment());// Se le asigna un valor a
                                                   // la// celda
                listitem.appendChild(cell);// Se añade la celda a la lista
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        /*
         * public void render(Listitem listitem, CPerson data, int index) {
         * Listcell cell = new Listcell(); listitem.appendChild(cell); if (data
         * instanceof String[]) { cell.appendChild(new Label(((String[])
         * data)[0].toString())); } else if (data instanceof String) {
         * cell.appendChild(new Label(data.toString())); } else {
         * cell.appendChild(new Label("UNKNOW:" + data.toString())); } } }
         */}

    public void doAfterCompose(Component comp) {
        try {
            super.doAfterCompose(comp);            
            CPerson persona1 = new CPerson("1", "Roger", "Paesani", "04129193576",1,LocalDate.parse("1995-08-16"),"Yo");
            CPerson persona2 = new CPerson("2", "Chito", "Narváez", "6942069",1,LocalDate.parse("2013-02-21"),"Perro");
            CPerson persona3 = new CPerson("3", "Asunción", "Narváez", "04160980720",0,LocalDate.parse("1967-05-28"),"Mamá");
            datamodelpersona.setMultiple(true);
            datamodelpersona.add(persona1);
            datamodelpersona.add(persona2);
            datamodelpersona.add(persona3);
            listboxpersons.setModel(datamodelpersona);
            listboxpersons.setItemRenderer(new MyRenderer());            
            CPerson Nuevo = (CPerson) execution.getArg().get("Data");
            if (Nuevo!=null){
                datamodelpersona.add(Nuevo);
                listboxpersons.setModel(datamodelpersona);
                listboxpersons.setItemRenderer((new MyRenderer()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Listen("onClick=#buttonadd")
    public void onClickbuttonadd(Event event) {
        // Map arg = new HashMap();
        // arg.put("someName", someValue);
        Window win = (Window) Executions.createComponents("/dialog.zul", null, null);
        win.doModal();
    }

    @Listen("onClick=#buttonmodify")
    public void onClickbuttonmodify(Event event) {
        Set<CPerson> selecteditems = datamodelpersona.getSelection();//Se crea una lista de personas con los elementos seleccionados
        if ((selecteditems != null) && (datamodelpersona.getSelection().size() > 0)) {//Si hay elementos
            CPerson person = selecteditems.iterator().next();
            Map<String,Object> arg = new HashMap<String,Object>();
            arg.put("personToModify", person);
            arg.put("buttonadd", buttonadd);
            arg.put("buttonmodify", buttonmodify);
            arg.put("ModifyModel", datamodelpersona);
            Window win = (Window) Executions.createComponents("/dialog.zul", null , arg);
            win.doModal();            
            windowmanager.detach();
        }else{ //sino
            Messagebox.show("       Error, no hay selección.", "Aceptar", Messagebox.OK, Messagebox.EXCLAMATION);
            //Se da un mensaje de error
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Listen("onClick=#buttondelete")
    public void onClickbuttondelete(Event event) {//Si se hace click en el botón borrar
        Set<CPerson> selecteditems = datamodelpersona.getSelection();//Se crea una lista de personas con los elementos seleccionados
        if ((selecteditems != null) && (datamodelpersona.getSelection().size() > 0)) {//Si hay elementos
            String buffer = null;//Se crea un buffer
            for (CPerson persona : selecteditems) {//Por cada persona seleccionada
                if (buffer == null) {//Si el buffer está vacío
                    buffer = persona.getStrci() + " " + persona.getnombre() + " " + persona.getapellido() + " "
                            + persona.gettelefono() + " ";//Se toma el primer elemento
                } else {//sino
                    buffer = buffer + "\n" + persona.getStrci() + " " + persona.getnombre() + " "+ persona.getapellido()+ " " + persona.gettelefono() + " ";//se toman el siguiente
                    // Messagebox.show(buffer, "Aceptar", Messagebox.OK,
                    // Messagebox.EXCLAMATION);
                }//fin si
            }//fin por
                Messagebox.show("¿Seguro que desea eliminar los " + Integer.toString(selecteditems.size())+ " elementos seleccionados?\n"+buffer,"Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,new org.zkoss.zk.ui.event.EventListener() {//Validación
                            public void onEvent(Event evt) throws InterruptedException {
                                if (evt.getName().equals("onOK")) {//Si la respuesta es sí
                                    alert("¡Elementos borrados!");//Se da un aviso
                                    while (selecteditems.iterator().hasNext()) {//mientras haya elementos seleccionados
                                        CPerson persona = selecteditems.iterator().next();//se toma el elemento
                                        //selecteditems.iterator().remove();
                                        datamodelpersona.remove(persona);//Se destruye
                                    }//fin mientras
                                }//fin si
                            }
                        });
            }else{ //sino
            Messagebox.show("       Error, no hay selección.", "Aceptar", Messagebox.OK, Messagebox.EXCLAMATION);
            //Se da un mensaje de error
        }
    }
}

/*
 * Hay un pequeño detalle con el buffer del 36:40, cuando se utiliza para más de
 * dos selecciones no te las entrega ordenadamente. Si se tiene: 1 2 3 4 5 y se
 * selecciona con shift + leftclick toda la lista, el resultado es: ;1;5;2;3;4
 * 
 * Además si se selecciona con ctrl + leftclick nos retorna el orden en el que
 * seleccionemos los elementos, digamos que yo selecciono el primer elemento,
 * luego el último y continuo de mayor a menor, el resultado sería: ;1;5;4;3;2.
 * 
 * ¿Hay alguna manera de hacer que el buffer lo reciba en orden?
 */

