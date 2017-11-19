package controller;

import dao.*;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.*;

@Named(value = "registrarEntradaInsumosLocalBean")
@ViewScoped
public class RegistrarEntradaInsumosLocalBean implements Serializable {

    private AbastecimientoDAO abastecimientoDAO;
    private Abastecimiento abastecimiento;
    private List<Abastecimiento> listAbastecimiento;

    private AbastecimientoHasInsumoDAO abastecimientoHasInsumoDAO;
    private List<AbastecimientoHasInsumo> listAbastecimientoHasInsumos;
    
    private Usuario usuario;

    private int idDiv;

    public RegistrarEntradaInsumosLocalBean() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usuario = (Usuario) httpSession.getAttribute("usuario");
        
        abastecimientoDAO = new AbastecimientoDAO();
        listAbastecimiento = abastecimientoDAO.getListAbastecimientoSolicitudestoLocal(usuario.getLocal());

        idDiv = 1;
    }

    public void registrarEntradaInsumosLocal() {
        LocalHasInsumoDAO localHasInsumoDAO = new LocalHasInsumoDAO();
        LocalHasInsumo localHasInsumo = null;
        for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumos) {
            localHasInsumo = localHasInsumoDAO.getLocalHasInsumo(usuario.getLocal(), item.getInsumo());
            if (localHasInsumo == null) {
                LocalHasInsumoId localHasInsumoId = new LocalHasInsumoId();
                localHasInsumoId.setInsumoId(item.getInsumo().getId());
                localHasInsumoId.setLocalId(usuario.getLocal().getId());

                localHasInsumo = new LocalHasInsumo();
                localHasInsumo.setId(localHasInsumoId);
                localHasInsumo.setInsumo(item.getInsumo());
                localHasInsumo.setLocal(usuario.getLocal());
                localHasInsumo.setCantidad(item.getCantidad());

                localHasInsumoDAO.create(localHasInsumo);
            } else {
                localHasInsumo.setCantidad(localHasInsumo.getCantidad() + item.getCantidad());
                localHasInsumoDAO.update(localHasInsumo);
            }
        }
        EstadoAbastecimiento estadoAbastecimiento = new EstadoAbastecimiento();
        estadoAbastecimiento.setId(4);
        abastecimiento.setEstadoAbastecimiento(estadoAbastecimiento);
        abastecimientoDAO.update(abastecimiento);

        listAbastecimiento = abastecimientoDAO.getListAbastecimientoSolicitudestoLocal(usuario.getLocal());

        changeViewIndex();
    }

    public void cargarDetalleAbastecimiento(Abastecimiento a) {
        abastecimiento = a;
        abastecimientoHasInsumoDAO = new AbastecimientoHasInsumoDAO();
        listAbastecimientoHasInsumos = abastecimientoHasInsumoDAO.getListAbastecimientoHasInsumo(abastecimiento);
    }

    public void changeViewIndex() {
        idDiv = 1;
    }

    public void changeViewRecibirAbastecimiento() {
        idDiv = 2;
    }

    public List<Abastecimiento> getListAbastecimiento() {
        return listAbastecimiento;
    }

    public void setListAbastecimiento(List<Abastecimiento> listAbastecimiento) {
        this.listAbastecimiento = listAbastecimiento;
    }

    public Abastecimiento getAbastecimiento() {
        return abastecimiento;
    }

    public void setAbastecimiento(Abastecimiento abastecimiento) {
        this.abastecimiento = abastecimiento;
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
