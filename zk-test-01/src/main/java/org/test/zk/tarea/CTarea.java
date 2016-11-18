package org.test.zk.tarea;

import java.io.Serializable;

public class CTarea implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5441381696886193672L;
    protected String tarea = "";
    protected String prioridad = "";

    public CTarea(String tarea, String prioridad) {
        super();
        this.tarea = tarea;
        this.prioridad = prioridad;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
}
