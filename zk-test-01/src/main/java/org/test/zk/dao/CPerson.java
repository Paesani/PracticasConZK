package org.test.zk.dao;

import java.io.Serializable;

public class CPerson implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -979979226322500387L;
    protected String strci;
    protected String strnombre;
    protected String strapellido;
    protected int inttelefono;

    public String getStrci() {
        return strci;
    }

    public void setci(String strci) {
        this.strci = strci;
    }

    public String getnombre() {
        return strnombre;
    }

    public CPerson(String strci, String strnombre, String strapellido, int strtelefono) {        
        this.strci = strci;
        this.strnombre = strnombre;
        this.strapellido = strapellido;
        this.inttelefono = strtelefono;
    }

    public void setnombre(String strnombre) {
        this.strnombre = strnombre;
    }

    public String getapellido() {
        return strapellido;
    }

    public void setapellido(String strapellido) {
        this.strapellido = strapellido;
    }

    public int gettelefono() {
        return inttelefono;
    }

    public void settelefono(int strtelefono) {
        this.inttelefono = strtelefono;
    }
}
