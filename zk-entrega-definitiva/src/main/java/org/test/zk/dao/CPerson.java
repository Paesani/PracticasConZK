package org.test.zk.dao;

import java.io.Serializable;
import java.time.LocalDate;

public class CPerson implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5616080817635217719L;    
    protected String strci;
    protected String strnombre;
    protected String strapellido;
    protected String inttelefono;
    protected int intgender;// 0 Mujer, 1 Hombre
    protected LocalDate cumple=null;
    protected String strcomment;

    public CPerson(String strci, String strnombre, String strapellido, String inttelefono, int intgender, LocalDate cumple, String strcomment) {        
        this.strci = strci;
        this.strnombre = strnombre;
        this.strapellido = strapellido;
        this.inttelefono = inttelefono;
        this.intgender = intgender;
        this.cumple = cumple;
        this.strcomment = strcomment;
    }

    public int getGender() {
        return intgender;
    }

    public void setGender(int intgender) {
        this.intgender = intgender;
    }

    public LocalDate getCumple() {
        return cumple;
    }

    public void setCumple(LocalDate cumple) {
        this.cumple = cumple;
    }

    public String getComment() {
        return strcomment;
    }

    public void setComment(String strcomment) {
        this.strcomment = strcomment;
    }

    public String getStrci() {
        return strci;
    }

    public void setci(String strci) {
        this.strci = strci;
    }

    public String getnombre() {
        return strnombre;
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

    public String gettelefono() {
        return inttelefono;
    }

    public void settelefono(String strtelefono) {
        this.inttelefono = strtelefono;
    }
}
