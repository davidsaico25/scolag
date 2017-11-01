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
    
    private Abastecimiento abastecimiento;
    
    private AbastecimientoHasInsumo abastecimientoHasInsumo;
    private int cantidad = 0;
    private List<AbastecimientoHasInsumo> listAbastecimientoHasInsumo;
    
    private LocalDAO localDAO;    
    private Local local;
    private int localId;
    private List<Local> listLocal;
    
    private int idDiv;

    public RegistrarSalidaInsumosBean() {        
        localHasInsumoDAO = new LocalHasInsumoDAO();
        listLocalHasInsumo = localHasInsumoDAO.getListLocalHasInsumo();
        listLocalHasInsumoActualizar = new ArrayList<>();
        
        listAbastecimientoHasInsumo = new ArrayList<>();
        
        abastecimiento = new Abastecimiento();
        
        localDAO = new LocalDAO();
        listLocal = localDAO.getListLocal();
        
        idDiv = 1;
    }
    
    public void addListAbastecimientoHasInsumo() {
        abastecimientoHasInsumo = new AbastecimientoHasInsumo();
        abastecimientoHasInsumo.setInsumo(localHasInsumo.getInsumo());
        abastecimientoHasInsumo.setCantidad(cantidad);
        listAbastecimientoHasInsumo.add(abastecimientoHasInsumo);
        
        listLocalHasInsumo.remove(localHasInsumo);
        localHasInsumo.setCantidad(localHasInsumo.getCantidad() - cantidad);
        listLocalHasInsumo.add(localHasInsumo);
        listLocalHasInsumoActualizar.add(localHasInsumo);
        
        cantidad = 0;
    }
    
    public void deleteInsumoFromlistLocalHasInsumoSalida(AbastecimientoHasInsumo ahi) {
        for (LocalHasInsumo item : listLocalHasInsumo) {
            if (item.getInsumo().getId() == ahi.getInsumo().getId()) {
                listLocalHasInsumo.remove(item);
                listLocalHasInsumoActualizar.remove(item);
                item.setCantidad(item.getCantidad() + ahi.getCantidad());
                listLocalHasInsumo.add(item);
                break;
            }
        }
        listAbastecimientoHasInsumo.remove(ahi);
    }
    
    public void confirmarRegistrarSalidaInsumos() {
        AbastecimientoDAO abastecimientoDAO = new AbastecimientoDAO();
        abastecimiento = new Abastecimiento();
        abastecimiento.setLocalByLocalIdOrigen(listLocal.get(0));
        for (Local item : listLocal) {
            if (item.getId() == localId) {
                local = item;
                break;
            }
        }
        abastecimiento.setLocalByLocalIdDestino(local);
        abastecimientoDAO.create(abastecimiento);
        
        AbastecimientoHasInsumoId abastecimientoHasInsumoId = new AbastecimientoHasInsumoId();
        abastecimientoHasInsumoId.setAbastecimientoId(abastecimiento.getId());
        
        
        AbastecimientoHasInsumoDAO abastecimientoHasInsumoDAO = new AbastecimientoHasInsumoDAO();
        for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumo) {
            abastecimientoHasInsumoId.setInsumoId(item.getInsumo().getId());
            item.setId(abastecimientoHasInsumoId);
            item.setAbastecimiento(abastecimiento);
            abastecimientoHasInsumoDAO.create(item);
        }
        
        for (LocalHasInsumo item : listLocalHasInsumo) {
            localHasInsumoDAO.update(item);
        }
        
        idDiv = 2;
    }
    
    public void resetParams() {
        localHasInsumo = new LocalHasInsumo();
        listLocalHasInsumoActualizar = new ArrayList<>();
        
        abastecimiento = new Abastecimiento();
    
        abastecimientoHasInsumo = new AbastecimientoHasInsumo();
        cantidad = 0;
        listAbastecimientoHasInsumo = new ArrayList<>();
        
        local = new Local();
        localId = 0;
    }

    public LocalHasInsumo getLocalHasInsumo() {
        return localHasInsumo;
    }

    public void setLocalHasInsumo(LocalHasInsumo localHasInsumo) {
        this.localHasInsumo = localHasInsumo;
    }

    public List<LocalHasInsumo> getListLocalHasInsumoActualizar() {
        return listLocalHasInsumoActualizar;
    }

    public void setListLocalHasInsumoActualizar(List<LocalHasInsumo> listLocalHasInsumoActualizar) {
        this.listLocalHasInsumoActualizar = listLocalHasInsumoActualizar;
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

    public List<AbastecimientoHasInsumo> getListAbastecimientoHasInsumo() {
        return listAbastecimientoHasInsumo;
    }

    public void setListAbastecimientoHasInsumo(List<AbastecimientoHasInsumo> listAbastecimientoHasInsumos) {
        this.listAbastecimientoHasInsumo = listAbastecimientoHasInsumos;
    }

    public Abastecimiento getAbastecimiento() {
        return abastecimiento;
    }

    public void setAbastecimiento(Abastecimiento abastecimiento) {
        this.abastecimiento = abastecimiento;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    public List<Local> getListLocal() {
        return listLocal;
    }

    public void setListLocal(List<Local> listLocal) {
        this.listLocal = listLocal;
    }

    public int getIdDiv() {
        return idDiv;
    }

    public void setIdDiv(int idDiv) {
        this.idDiv = idDiv;
    }
    
}
