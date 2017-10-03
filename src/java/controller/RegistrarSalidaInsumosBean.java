package controller;

import dao.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.*;

@Named(value = "registrarSalidaInsumosBean")
@ViewScoped
public class RegistrarSalidaInsumosBean implements Serializable {
    
    private LocalHasInsumoDAO localHasInsumoDAO;
    private LocalHasInsumo localHasInsumo;
    private List<LocalHasInsumo> listLocalHasInsumo;
    private List<LocalHasInsumo> listLocalHasInsumoActualizar;
    
    private AbastecimientoDAO abastecimientoDAO;
    private Abastecimiento abastecimiento;
    
    private AbastecimientoHasInsumosDAO abastecimientoHasInsumosDAO;
    private AbastecimientoHasInsumo abastecimientoHasInsumo;
    private int cantidad = 0;
    private List<AbastecimientoHasInsumo> listAbastecimientoHasInsumos;
    
    private int idDiv;

    public RegistrarSalidaInsumosBean() {
        localHasInsumoDAO = new LocalHasInsumoDAO();
        listLocalHasInsumo = localHasInsumoDAO.getListLocalHasInsumo();
        listLocalHasInsumoActualizar = new ArrayList<>();
        
        abastecimiento = new Abastecimiento();
        
        idDiv = 1;
    }
    
    public void addListAbastecimientoHasInsums() {
        abastecimientoHasInsumo = new AbastecimientoHasInsumo();
        abastecimientoHasInsumo.setInsumo(localHasInsumo.getInsumo());
        abastecimientoHasInsumo.setCantidad(cantidad);
        
    }

    public LocalHasInsumo getLocalHasInsumo() {
        return localHasInsumo;
    }

    public void setLocalHasInsumo(LocalHasInsumo localHasInsumo) {
        this.localHasInsumo = localHasInsumo;
    }

    public List<LocalHasInsumo> getListLocalHasInsumo() {
        return listLocalHasInsumo;
    }

    public void setListLocalHasInsumo(List<LocalHasInsumo> listLocalHasInsumo) {
        this.listLocalHasInsumo = listLocalHasInsumo;
    }

    public AbastecimientoHasInsumo getAbastecimientoHasInsumo() {
        return abastecimientoHasInsumo;
    }

    public void setAbastecimientoHasInsumo(AbastecimientoHasInsumo abastecimientoHasInsumo) {
        this.abastecimientoHasInsumo = abastecimientoHasInsumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<AbastecimientoHasInsumo> getListAbastecimientoHasInsumos() {
        return listAbastecimientoHasInsumos;
    }

    public void setListAbastecimientoHasInsumos(List<AbastecimientoHasInsumo> listAbastecimientoHasInsumos) {
        this.listAbastecimientoHasInsumos = listAbastecimientoHasInsumos;
    }

    public int getIdDiv() {
        return idDiv;
    }

    public void setIdDiv(int idDiv) {
        this.idDiv = idDiv;
    }
    
}
