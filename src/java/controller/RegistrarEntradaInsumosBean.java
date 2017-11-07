package controller;

import dao.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.*;

@Named(value = "registrarEntradaInsumosBean")
@ViewScoped
public class RegistrarEntradaInsumosBean implements Serializable {

    PresentacionInsumoDAO presentacionInsumoDAO;
    private PresentacionInsumo presentacionInsumo;
    private List<PresentacionInsumo> listPresentacionInsumo;
    private List<PresentacionInsumo> listPresentacionInsumoEntrada;

    private PresentacionInsumoCantidad presentacionInsumoCantidad;
    private List<PresentacionInsumoCantidad> listPresentacionInsumoCantidad;

    private Abastecimiento abastecimiento;

    private AbastecimientoHasInsumoDAO abastecimientoHasInsumoDAO;
    private AbastecimientoHasInsumo abastecimientoHasInsumo;
    private List<AbastecimientoHasInsumo> listAbastecimientoHasInsumo;

    private int cantidad;

    private int idDiv;

    public RegistrarEntradaInsumosBean() {
        presentacionInsumoDAO = new PresentacionInsumoDAO();
        listPresentacionInsumo = presentacionInsumoDAO.getListPresentacionInsumo();
        listPresentacionInsumoEntrada = new ArrayList<>();
        listPresentacionInsumoCantidad = new ArrayList<>();

        listAbastecimientoHasInsumo = new ArrayList<>();

        idDiv = 1;
    }

    public void addListAbastecimientoHasInsumo() {
        listPresentacionInsumoEntrada.add(presentacionInsumo);
        
        presentacionInsumoCantidad = new PresentacionInsumoCantidad();
        presentacionInsumoCantidad.setPresentacionInsumo(presentacionInsumo);
        presentacionInsumoCantidad.setCantidad(cantidad);
        listPresentacionInsumoCantidad.add(presentacionInsumoCantidad);

        abastecimientoHasInsumo = new AbastecimientoHasInsumo();
        abastecimientoHasInsumo.setInsumo(presentacionInsumo.getInsumo());
        abastecimientoHasInsumo.setCantidad(presentacionInsumo.getRendimiento() * cantidad);
        listAbastecimientoHasInsumo.add(abastecimientoHasInsumo);

        cantidad = 0;
    }

    public void deleteInsumoFromlistLocalHasInsumoSalida(PresentacionInsumoCantidad pic) {
        listPresentacionInsumoEntrada.remove(pic.getPresentacionInsumo());
        
        listPresentacionInsumoCantidad.remove(pic);
        
        for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumo) {
            if (item.getInsumo().equals(pic.getPresentacionInsumo().getInsumo())) {
                listAbastecimientoHasInsumo.remove(item);
                break;
            }
        }
    }
    
    public void confirmarRegistrarEntradaInsumos() {
        for (PresentacionInsumo item : listPresentacionInsumoEntrada) {
            System.out.println(item.getInsumo().getNombre());
        }
        for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumo) {
            System.out.println(item.getInsumo().getNombre() + " - " + item.getCantidad());
        }
    }

    public PresentacionInsumo getPresentacionInsumo() {
        return presentacionInsumo;
    }

    public void setPresentacionInsumo(PresentacionInsumo presentacionInsumo) {
        this.presentacionInsumo = presentacionInsumo;
    }

    public List<PresentacionInsumo> getListPresentacionInsumo() {
        return listPresentacionInsumo;
    }

    public void setListPresentacionInsumo(List<PresentacionInsumo> listPresentacionInsumo) {
        this.listPresentacionInsumo = listPresentacionInsumo;
    }

    public List<PresentacionInsumo> getListPresentacionInsumoEntrada() {
        return listPresentacionInsumoEntrada;
    }

    public void setListPresentacionInsumoEntrada(List<PresentacionInsumo> listPresentacionInsumoEntrada) {
        this.listPresentacionInsumoEntrada = listPresentacionInsumoEntrada;
    }

    public PresentacionInsumoCantidad getPresentacionInsumoCantidad() {
        return presentacionInsumoCantidad;
    }

    public void setPresentacionInsumoCantidad(PresentacionInsumoCantidad presentacionInsumoCantidad) {
        this.presentacionInsumoCantidad = presentacionInsumoCantidad;
    }

    public List<PresentacionInsumoCantidad> getListPresentacionInsumoCantidad() {
        return listPresentacionInsumoCantidad;
    }

    public void setListPresentacionInsumoCantidad(List<PresentacionInsumoCantidad> listPresentacionInsumoCantidad) {
        this.listPresentacionInsumoCantidad = listPresentacionInsumoCantidad;
    }

    public List<AbastecimientoHasInsumo> getListAbastecimientoHasInsumo() {
        return listAbastecimientoHasInsumo;
    }

    public void setListAbastecimientoHasInsumo(List<AbastecimientoHasInsumo> listAbastecimientoHasInsumo) {
        this.listAbastecimientoHasInsumo = listAbastecimientoHasInsumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdDiv() {
        return idDiv;
    }

    public void setIdDiv(int idDiv) {
        this.idDiv = idDiv;
    }

    public class PresentacionInsumoCantidad implements Serializable {

        private PresentacionInsumo presentacionInsumo;
        private int cantidad;

        public PresentacionInsumo getPresentacionInsumo() {
            return presentacionInsumo;
        }

        public void setPresentacionInsumo(PresentacionInsumo presentacionInsumo) {
            this.presentacionInsumo = presentacionInsumo;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }
}
