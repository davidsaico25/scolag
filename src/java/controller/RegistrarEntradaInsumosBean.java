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
    
    private LocalHasInsumoDAO localHasInsumoDAO;
    private LocalHasInsumo localHasInsumo;
    private List<LocalHasInsumo> listLocalHasInsumo;
    private List<LocalHasInsumo> listLocalHasInsumoActualizar;
    
    private Abastecimiento abastecimiento;
    private AbastecimientoHasInsumo abastecimientoHasInsumo;
    private int cantidad = 0;
    private List<AbastecimientoHasInsumo> listAbastecimientoHasInsumos;
    
    private Local local;
    
    private int idDiv;
    
    public RegistrarEntradaInsumosBean() {
        localHasInsumoDAO = new LocalHasInsumoDAO();
        listLocalHasInsumo = localHasInsumoDAO.getListLocalHasInsumo();
        listAbastecimientoHasInsumos = new ArrayList<>();
        
        listLocalHasInsumoActualizar = new ArrayList<>();
        
        local = new LocalDAO().getListLocal().get(0);
        
        idDiv = 1;
    }
    
    public void addListAbastecimientoHasInsums() {
        abastecimientoHasInsumo = new AbastecimientoHasInsumo();
        abastecimientoHasInsumo.setInsumo(localHasInsumo.getInsumo());
        abastecimientoHasInsumo.setCantidad(cantidad);
        listAbastecimientoHasInsumos.add(abastecimientoHasInsumo);
        
        listLocalHasInsumo.remove(localHasInsumo);
        localHasInsumo.setCantidad(localHasInsumo.getCantidad() + cantidad);
        listLocalHasInsumo.add(localHasInsumo);
        listLocalHasInsumoActualizar.add(localHasInsumo);
        
        cantidad = 0;
    }
    
    public void mostrarListas() {
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("listLocalHasInsumo");
        for (LocalHasInsumo item : listLocalHasInsumo) {
            System.out.println(item.getInsumo().getNombre() + ": " + item.getCantidad());
        }
        System.out.println("listLocalHasInsumoActualizar");
        for (LocalHasInsumo item : listLocalHasInsumoActualizar) {
            System.out.println(item.getInsumo().getNombre() + ": " + item.getCantidad());
        }
        System.out.println("listAbastecimientoHasInsumos");
        for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumos) {
            System.out.println(item.getInsumo().getNombre() + ": " + item.getCantidad());
        }
    }
    
    public void deleteInsumoFromlistLocalHasInsumoSalida(AbastecimientoHasInsumo ahi) {
        System.out.println("AbastecimientoHasInsumo: " + ahi.getInsumo().getId());
        
        for (LocalHasInsumo item : listLocalHasInsumo) {
            System.out.println("item: " + item.getInsumo().getId());
            if (item.getInsumo().getId() == ahi.getInsumo().getId()) {
                System.out.println("entro: " + item.getInsumo().getId());
                listLocalHasInsumo.remove(item);
                listLocalHasInsumoActualizar.remove(item);
                item.setCantidad(item.getCantidad() - ahi.getCantidad());
                listLocalHasInsumo.add(item);
                break;
            }
        }
        listAbastecimientoHasInsumos.remove(ahi);
    }
    
    public void confirmarRegistrarEntradaInsumos() {
        AbastecimientoDAO abastecimientoDAO = new AbastecimientoDAO();
        abastecimiento = new Abastecimiento();
        abastecimiento.setLocalByLocalIdOrigen(null);
        abastecimiento.setLocalByLocalIdDestino(local);
        abastecimientoDAO.create(abastecimiento);
        
        AbastecimientoHasInsumoId abastecimientoHasInsumoId = new AbastecimientoHasInsumoId();
        abastecimientoHasInsumoId.setAbastecimientoId(abastecimiento.getId());
        
        
        AbastecimientoHasInsumoDAO abastecimientoHasInsumosDAO = new AbastecimientoHasInsumoDAO();
        for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumos) {
            abastecimientoHasInsumoId.setInsumoId(item.getInsumo().getId());
            item.setId(abastecimientoHasInsumoId);
            item.setAbastecimiento(abastecimiento);
            abastecimientoHasInsumosDAO.create(item);
        }
        
        for (LocalHasInsumo item : listLocalHasInsumo) {
            localHasInsumoDAO.update(item);
        }
        
        idDiv = 2;
        
        mostrarListas();
    }
    
    public void resetParams() {
        localHasInsumo = new LocalHasInsumo();
        listLocalHasInsumoActualizar = new ArrayList<>();
        
        abastecimiento = new Abastecimiento();
    
        abastecimientoHasInsumo = new AbastecimientoHasInsumo();
        cantidad = 0;
        listAbastecimientoHasInsumos = new ArrayList<>();
        
        local = new Local();
    }
    
    public void changeViewNuevaEntrada() {
        idDiv = 2;
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

    public Abastecimiento getAbastecimiento() {
        return abastecimiento;
    }

    public void setAbastecimiento(Abastecimiento abastecimiento) {
        this.abastecimiento = abastecimiento;
    }

    public AbastecimientoHasInsumo getAbastecimientoHasInsumo() {
        return abastecimientoHasInsumo;
    }

    public void setAbastecimientoHasInsumo(AbastecimientoHasInsumo abastecimientoHasInsumo) {
        this.abastecimientoHasInsumo = abastecimientoHasInsumo;
    }

    public List<AbastecimientoHasInsumo> getListAbastecimientoHasInsumos() {
        return listAbastecimientoHasInsumos;
    }

    public void setListAbastecimientoHasInsumos(List<AbastecimientoHasInsumo> listAbastecimientoHasInsumos) {
        this.listAbastecimientoHasInsumos = listAbastecimientoHasInsumos;
    }

    public Local getLocal() {
        if (local == null) {
            local = new LocalDAO().getListLocal().get(0);
        }
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public List<LocalHasInsumo> getListLocalHasInsumoActualizar() {
        return listLocalHasInsumoActualizar;
    }

    public void setListLocalHasInsumoActualizar(List<LocalHasInsumo> listLocalHasInsumoActualizar) {
        this.listLocalHasInsumoActualizar = listLocalHasInsumoActualizar;
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
}