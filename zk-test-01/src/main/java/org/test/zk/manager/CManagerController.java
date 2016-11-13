package org.test.zk.manager;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

public class CManagerController extends SelectorComposer<Component> {

    /**
     * 
     */
    private static final long serialVersionUID = -1591648938821366036L;
        @Wire
        Button buttonadd;
        @Wire
        Button buttonmodify;
        @Wire
        Button buttondelete;
        @Wire
        Listbox listboxpersons;
        @Listen ("onClick=#buttonadd")
        public void onClickbuttonadd (Event event){
            //Map arg = new HashMap();
            //arg.put("someName", someValue);
            Window win = (Window) Executions.createComponents("/dialog.zul", null, null);
            win.doModal();
        }
        @Listen ("onClick=#buttonmodify")
        public void onClickbuttonmodify (Event event){
            
        }
        @Listen ("onClick=#buttondelete")
        public void onClickbuttondelete (Event event){
            
        }        
}
