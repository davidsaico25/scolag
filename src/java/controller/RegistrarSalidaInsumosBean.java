package controller;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "registrarEntradaInsumosBean")
@ViewScoped
public class RegistrarSalidaInsumosBean implements Serializable {
    
    private int idDiv;

    public RegistrarSalidaInsumosBean() {
        idDiv = 1;
    }
    
    public void cambiarView() {
        idDiv = 2;
    }

    public int getIdDiv() {
        return idDiv;
    }

    public void setIdDiv(int idDiv) {
        this.idDiv = idDiv;
    }
    
}
