package controller;

import dao.*;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.*;

@Named(value = "registrarEntradaInsumosBean")
@ViewScoped
public class RegistrarEntradaInsumosBean implements Serializable {

    private OrdenCompraDAO ordenCompraDAO;
    private List<OrdenCompra> listOrdenCompra;
    private OrdenCompra ordenCompra;
    
    private OrdenCompraHasPresentacionInsumoDAO ordenCompraHasPresentacionInsumoDAO;
    private List<OrdenCompraHasPresentacionInsumo> listOrdenCompraHasPresentacionInsumo;
    private OrdenCompraHasPresentacionInsumo ordenCompraHasPresentacionInsumo;
    
    private int idDiv;

    public RegistrarEntradaInsumosBean() {
        ordenCompraDAO = new OrdenCompraDAO();
        listOrdenCompra = ordenCompraDAO.getListOrdenCompra();
        
        ordenCompraHasPresentacionInsumoDAO = new OrdenCompraHasPresentacionInsumoDAO();

        idDiv = 1;
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

    public int getIdDiv() {
        return idDiv;
    }

    public void setIdDiv(int idDiv) {
        this.idDiv = idDiv;
    }
}
