package model;

import java.util.List;

public class Perfil {
    
    String nombre;
    List<Modulo> listModulo;
    
    public Perfil(String nombre, List<Modulo> listModulo) {
        this.nombre = nombre;
        this.listModulo = listModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<Modulo> getListModulo() {
        return listModulo;
    }

    public void setListModulo(List<Modulo> listModulo) {
        this.listModulo = listModulo;
    }
}
