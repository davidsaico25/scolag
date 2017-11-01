package model;
// Generated Oct 31, 2017 7:02:24 PM by Hibernate Tools 4.3.1



/**
 * RequerimientoHasPresentacionInsumo generated by hbm2java
 */
public class RequerimientoHasPresentacionInsumo  implements java.io.Serializable {


     private RequerimientoHasPresentacionInsumoId id;
     private PresentacionInsumo presentacionInsumo;
     private Requerimiento requerimiento;
     private double precioUnitario;
     private int cantidad;

    public RequerimientoHasPresentacionInsumo() {
    }

    public RequerimientoHasPresentacionInsumo(RequerimientoHasPresentacionInsumoId id, PresentacionInsumo presentacionInsumo, Requerimiento requerimiento, double precioUnitario, int cantidad) {
       this.id = id;
       this.presentacionInsumo = presentacionInsumo;
       this.requerimiento = requerimiento;
       this.precioUnitario = precioUnitario;
       this.cantidad = cantidad;
    }
   
    public RequerimientoHasPresentacionInsumoId getId() {
        return this.id;
    }
    
    public void setId(RequerimientoHasPresentacionInsumoId id) {
        this.id = id;
    }
    public PresentacionInsumo getPresentacionInsumo() {
        return this.presentacionInsumo;
    }
    
    public void setPresentacionInsumo(PresentacionInsumo presentacionInsumo) {
        this.presentacionInsumo = presentacionInsumo;
    }
    public Requerimiento getRequerimiento() {
        return this.requerimiento;
    }
    
    public void setRequerimiento(Requerimiento requerimiento) {
        this.requerimiento = requerimiento;
    }
    public double getPrecioUnitario() {
        return this.precioUnitario;
    }
    
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }




}


