package model;

import java.util.List;

public class Perfil {
    
    String nombre;
    String paquete;
    List<Modulo> listModulo;
    
    public Perfil(String nombre, String paquete, List<Modulo> listModulo) {
        this.nombre = nombre;
        this.paquete = paquete;
        this.listModulo = listModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public List<Modulo> getListModulo() {
        return listModulo;
    }

    public void setListModulo(List<Modulo> listModulo) {
        this.listModulo = listModulo;
    }
}
