package model;

public class Modulo {
    
    String nombre;
    String outcome;
    
    public Modulo(String nombre, String url) {
        this.nombre = nombre;
        this.outcome = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}