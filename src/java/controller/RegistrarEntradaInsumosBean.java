package controller;

import dao.*;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.*;

@Named(value = "registrarEntradaInsumosBean")
@ViewScoped
public class RegistrarEntradaInsumosBean implements Serializable {

    private OrdenCompraDAO ordenCompraDAO;
    private List<OrdenCompra> listOrdenCompra;
    private OrdenCompra ordenCompra;
    
    private OrdenCompraHasPresentacionInsumoDAO ordenCompraHasPresentacionInsumoDAO;
    private List<OrdenCompraHasPresentacionInsumo> listOrdenCompraHasPresentacionInsumo;
    
    private Usuario usuario;
    
    private String password;
    
    private int idDiv;

    public RegistrarEntradaInsumosBean() {
        ordenCompraDAO = new OrdenCompraDAO();
        listOrdenCompra = ordenCompraDAO.getListOrdenCompra();
        
        ordenCompraHasPresentacionInsumoDAO = new OrdenCompraHasPresentacionInsumoDAO();
        
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usuario = (Usuario) httpSession.getAttribute("usuario");

        idDiv = 1;
    }
    
    public void registrarEntradaInsumos() {
        LocalHasInsumoDAO localHasInsumoDAO = new LocalHasInsumoDAO();
        LocalHasInsumo localHasInsumo = null;
        for (OrdenCompraHasPresentacionInsumo item : listOrdenCompraHasPresentacionInsumo) {
            localHasInsumo = localHasInsumoDAO.getLocalHasInsumo(item.getPresentacionInsumo().getInsumo());
            localHasInsumo.setCantidad(localHasInsumo.getCantidad() + (item.getPresentacionInsumo().getRendimiento() * item.getCantidad()));
            localHasInsumoDAO.update(localHasInsumo);
        }
        EstadoOrdenCompra estadoOrdenCompra = new EstadoOrdenCompra();
        estadoOrdenCompra.setId(2);
        ordenCompra.setEstadoOrdenCompra(estadoOrdenCompra);
        ordenCompraDAO.update(ordenCompra);
        
        listOrdenCompra = ordenCompraDAO.getListOrdenCompra();
        
        changeViewIndex();
    }
    
    public void cargarDetalleOrdenCompra(OrdenCompra oc) {
        ordenCompra = oc;
        listOrdenCompraHasPresentacionInsumo = ordenCompraHasPresentacionInsumoDAO.getListOrdenCompraHasPresentacionInsumo(ordenCompra);
    }
    
    public void changeViewIndex() {
        idDiv = 1;
    }
    
    public void changeViewRecibirOrdenCompra() {
        idDiv = 2;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public List<OrdenCompra> getListOrdenCompra() {
        return listOrdenCompra;
    }

    public void setListOrdenCompra(List<OrdenCompra> listOrdenCompra) {
        this.listOrdenCompra = listOrdenCompra;
    }

    public List<OrdenCompraHasPresentacionInsumo> getListOrdenCompraHasPresentacionInsumo() {
        return listOrdenCompraHasPresentacionInsumo;
    }

    public void setListOrdenCompraHasPresentacionInsumo(List<OrdenCompraHasPresentacionInsumo> listOrdenCompraHasPresentacionInsumo) {
        this.listOrdenCompraHasPresentacionInsumo = listOrdenCompraHasPresentacionInsumo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdDiv() {
        return idDiv;
    }

    public void setIdDiv(int idDiv) {
        this.idDiv = idDiv;
    }
}
