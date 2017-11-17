package controller;

import dao.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.*;

@Named(value = "crearSolicitudInsumoBean")
@ViewScoped
public class CrearSolicitudInsumoBean implements Serializable {

    private InsumoDAO insumoDAO;
    private List<Insumo> listInsumo;
    private List<Insumo> listInsumoSolicitud;
    private Insumo insumo;

    private Abastecimiento abastecimiento;

    private AbastecimientoHasInsumo abastecimientoHasInsumo;
    private List<AbastecimientoHasInsumo> listAbastecimientoHasInsumos;
    
    private Usuario usuario;

    private double cantidad;
    
    private Map<String, String> map;

    private int idDiv;

    public CrearSolicitudInsumoBean() {
        map = new HashMap<>();
        
        insumoDAO = new InsumoDAO();
        listInsumo = insumoDAO.getListInsumo();
        listAbastecimientoHasInsumos = new ArrayList<>();
        listInsumoSolicitud = new ArrayList<>();

        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usuario = (Usuario) httpSession.getAttribute("usuario");

        idDiv = 1;
    }

    public void confirmarSolicitudInsumo() {
        AbastecimientoDAO abastecimientoDAO = new AbastecimientoDAO();
        abastecimiento = new Abastecimiento();
        abastecimiento.setLocalByLocalIdDestino(usuario.getLocal());

        EstadoAbastecimiento estadoAbastecimiento = new EstadoAbastecimiento();
        estadoAbastecimiento.setId(1);

        abastecimiento.setEstadoAbastecimiento(estadoAbastecimiento);

        abastecimientoDAO.create(abastecimiento);

        AbastecimientoHasInsumoId abastecimientoHasInsumoId = new AbastecimientoHasInsumoId();
        abastecimientoHasInsumoId.setAbastecimientoId(abastecimiento.getId());

        AbastecimientoHasInsumoDAO abastecimientoHasInsumoDAO = new AbastecimientoHasInsumoDAO();
        for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumos) {
            abastecimientoHasInsumoId.setInsumoId(item.getInsumo().getId());
            item.setId(abastecimientoHasInsumoId);
            item.setAbastecimiento(abastecimiento);
            abastecimientoHasInsumoDAO.create(item);
        }

        idDiv = 2;
    }

    public void addInsumo() {
        if (cantidad < 0) {
            map.put("messageFormDataTableInsumos", "No se permite cantidades negativas");
            map.put("messageTypeFormDataTableInsumos", "warning");
            cantidad = 0;
            return;
        }
        map.clear();
        
        listInsumoSolicitud.add(insumo);
        abastecimientoHasInsumo = new AbastecimientoHasInsumo();
        abastecimientoHasInsumo.setInsumo(insumo);
        abastecimientoHasInsumo.setCantidad(cantidad);
        listAbastecimientoHasInsumos.add(abastecimientoHasInsumo);

        cantidad = 0;
    }

    public void removeInsumo(AbastecimientoHasInsumo ahi) {
        listAbastecimientoHasInsumos.remove(ahi);
        listInsumoSolicitud.remove(ahi.getInsumo());
    }

    public void resetParams() {
        abastecimiento = new Abastecimiento();
        listAbastecimientoHasInsumos = new ArrayList<>();
        listInsumoSolicitud = new ArrayList<>();
    }

    public Abastecimiento getAbastecimiento() {
        return abastecimiento;
    }

    public void setAbastecimiento(Abastecimiento abastecimiento) {
        this.abastecimiento = abastecimiento;
    }

    public int getIdDiv() {
        return idDiv;
    }

    public void setIdDiv(int idDiv) {
        this.idDiv = idDiv;
    }

    public List<Insumo> getListInsumo() {
        return listInsumo;
    }

    public void setListInsumo(List<Insumo> listInsumo) {
        this.listInsumo = listInsumo;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public List<Insumo> getListInsumoSolicitud() {
        return listInsumoSolicitud;
    }

    public void setListInsumoSolicitud(List<Insumo> listInsumoSolicitud) {
        this.listInsumoSolicitud = listInsumoSolicitud;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public List<AbastecimientoHasInsumo> getListAbastecimientoHasInsumos() {
        return listAbastecimientoHasInsumos;
    }

    public void setListAbastecimientoHasInsumos(List<AbastecimientoHasInsumo> listAbastecimientoHasInsumos) {
        this.listAbastecimientoHasInsumos = listAbastecimientoHasInsumos;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
