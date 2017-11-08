package model;
// Generated Nov 7, 2017 7:30:44 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Imagen generated by hbm2java
 */
public class Imagen  implements java.io.Serializable {


     private Integer id;
     private String descripcion;
     private String url;
     private Set presentacionInsumos = new HashSet(0);

    public Imagen() {
    }

	
    public Imagen(String descripcion, String url) {
        this.descripcion = descripcion;
        this.url = url;
    }
    public Imagen(String descripcion, String url, Set presentacionInsumos) {
       this.descripcion = descripcion;
       this.url = url;
       this.presentacionInsumos = presentacionInsumos;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public Set getPresentacionInsumos() {
        return this.presentacionInsumos;
    }
    
    public void setPresentacionInsumos(Set presentacionInsumos) {
        this.presentacionInsumos = presentacionInsumos;
    }




}


