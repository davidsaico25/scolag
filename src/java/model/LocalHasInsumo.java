package model;
// Generated Nov 7, 2017 7:30:44 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * LocalHasInsumo generated by hbm2java
 */
public class LocalHasInsumo  implements java.io.Serializable {


     private LocalHasInsumoId id;
     private Insumo insumo;
     private Local local;
     private double cantidad;
     private Date fechaModificacion;

    public LocalHasInsumo() {
    }

	
    public LocalHasInsumo(LocalHasInsumoId id, Insumo insumo, Local local, double cantidad) {
        this.id = id;
        this.insumo = insumo;
        this.local = local;
        this.cantidad = cantidad;
    }
    public LocalHasInsumo(LocalHasInsumoId id, Insumo insumo, Local local, double cantidad, Date fechaModificacion) {
       this.id = id;
       this.insumo = insumo;
       this.local = local;
       this.cantidad = cantidad;
       this.fechaModificacion = fechaModificacion;
    }
   
    public LocalHasInsumoId getId() {
        return this.id;
    }
    
    public void setId(LocalHasInsumoId id) {
        this.id = id;
    }
    public Insumo getInsumo() {
        return this.insumo;
    }
    
    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }
    public Local getLocal() {
        return this.local;
    }
    
    public void setLocal(Local local) {
        this.local = local;
    }
    public double getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }




}


