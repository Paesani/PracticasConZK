package org.test.zk;

import org.test.zk.dao.CPerson;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ItemRenderer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Window;

public class CTest01Controller extends SelectorComposer<Component> implements ItemRenderer<CPerson> {

    /**
     * 
     */
    private static final long serialVersionUID = -258902408111073465L;
    boolean aux = true;
    boolean titlechange = true;
    String ci = "";
    String Nombre ="";
    String Apellido ="";
    int Telefono =0;
    @Wire
    Button buttontest01;
    @Wire
    Window windowtest01;
    @Wire
    Button buttontest02;
    @Wire
    Button buttontest03;
    @Wire
    Button buttontest04;
    @Wire
    Button buttontest05;
    @Wire
    Button buttontest06;
    @Wire
    Button buttontest07;
    @Wire
    Selectbox selectbox1;
    @Wire
    Selectbox selectbox2;
    protected ListModelList<String> datamodel = new ListModelList<String>();
    protected ListModelList<CPerson> datamodelpersona = new ListModelList<CPerson>();
    @Listen("onClick=#buttontest01")
    public void onClickButtonTest01(Event event) {
        buttontest01.setLabel("botón inutil");
    }

    @Listen("onClick=#buttontest02")
    public void onClickButtonTest02(Event event) {
        buttontest02.setLabel("¡Mentí!");
    }

    @Listen("onClick=#buttontest03")
    public void onClickButtonTest03(Event event) {
        windowtest01.setVisible(false);
    }

    public boolean windowcheck(boolean check) {
        if (check == true) {
            buttontest04.setLabel("Maximizar");
            windowtest01.setHeight("250px");
            windowtest01.setWidth("250px");
            check = false;
            return check;
        } else {
            buttontest04.setLabel("Minimizar");
            windowtest01.setHeight("550px");
            windowtest01.setWidth("750px");
            check = true;
            return check;
        }
    }

    @Listen("onClick=#buttontest04")
    public void onClickButtonTest04(Event event) {
        aux = windowcheck(aux);
    }

    @Listen("onClick=#buttontest05")
    public void onClickButtonTest05(Event event) {
        datamodel.add("1");
        datamodel.add("2");
        datamodel.add("3");
        datamodel.add("4");
        datamodel.add("5");
        datamodel.add("6");
        selectbox1.setModel(datamodel);
        datamodel.addToSelection("1");
        selectbox1.setSelectedIndex(0);
    }
    @Listen("onClick=#buttontest06")
    public void onClickButtonTest06(Event event){
        datamodelpersona.add(new CPerson(ci,Nombre,Apellido,Telefono));
        ci = ci+1;
        Nombre = Nombre.concat("a");
        Apellido = Apellido.concat("b");
        Telefono = Telefono-1;
        selectbox2.setModel(datamodelpersona);
        selectbox2.setSelectedIndex(0);     
        selectbox2.setItemRenderer(this);
    }

    @Listen("onSelect=#selectbox1")
    public void onSelectselectbox1(Event event) {
        if ((selectbox1.getSelectedIndex() >= 0) && (selectbox1.getSelectedIndex() <= 3)) {
            windowtest01.setTitle(datamodel.get(selectbox1.getSelectedIndex()));
        }        
    }
    @Listen("onSelect=#selectbox2")
    public void onSelectselectbox2(Event event){
        CPerson Seleccion = datamodelpersona.get(selectbox2.getSelectedIndex());
        windowtest01.setTitle(Seleccion.getStrci()+" "+Seleccion.getnombre()+" "+Seleccion.getapellido()+" "+Seleccion.gettelefono());
    }

    @Override
    public String render(Component arg0, CPerson arg1, int arg2) throws Exception {
        return arg1.getnombre()+" "+arg1.getapellido()+" "+arg1.getStrci()+" "+arg1.gettelefono();
    }
}
