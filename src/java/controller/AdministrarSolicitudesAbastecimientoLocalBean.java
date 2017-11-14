package controller;

import dao.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.*;

@Named(value = "administrarSolicitudesAbastecimientoLocalBean")
@ViewScoped
public class AdministrarSolicitudesAbastecimientoLocalBean implements Serializable {

    private AbastecimientoDAO abastecimientoDAO;
    private List<Abastecimiento> listAbastecimiento;
    private Abastecimiento abastecimiento;

    private AbastecimientoHasInsumoDAO abastecimientoHasInsumoDAO;
    private List<AbastecimientoHasInsumo> listAbastecimientoHasInsumo;

    private EstadoAbastecimientoDAO estadoAbastecimientoDAO;
    private List<EstadoAbastecimiento> listEstadoAbastecimiento;
    private EstadoAbastecimiento estadoAbastecimiento;
    private int estadoAbastecimientoID;

    private Map<String, String> map;

    private int idDiv;

    public AdministrarSolicitudesAbastecimientoLocalBean() {
        map = new HashMap<>();

        abastecimientoDAO = new AbastecimientoDAO();
        listAbastecimiento = abastecimientoDAO.getListAbastecimientoSolicitudes();

        idDiv = 1;
    }

    public void guardarSolicitudAbastecimientoHasInsumo() {
        for (EstadoAbastecimiento item : listEstadoAbastecimiento) {
            if (item.getId() == estadoAbastecimientoID) {
                estadoAbastecimiento = item;
                break;
            }
        }

        if (estadoAbastecimiento.getId() == 2) {
            LocalHasInsumoDAO localHasInsumoDAO = new LocalHasInsumoDAO();
            for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumo) {
                LocalHasInsumo localHasInsumo = localHasInsumoDAO.getLocalHasInsumo(item.getInsumo());
                if (localHasInsumo.getCantidad() < item.getCantidad()) {
                    map.put("messageFormAbastecimientoDetail", "No tiene suficientes insumos en almacen, tendra que postergar la solicitud");
                    map.put("messageTypeFormAbastecimientoDetail", "danger");
                    return;
                }
            }

            for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumo) {
                LocalHasInsumo localHasInsumo = localHasInsumoDAO.getLocalHasInsumo(item.getInsumo());
                localHasInsumo.setCantidad(localHasInsumo.getCantidad() - item.getCantidad());
                localHasInsumoDAO.update(localHasInsumo);
            }
        }

        abastecimiento.setEstadoAbastecimiento(estadoAbastecimiento);
        abastecimientoDAO.update(abastecimiento);

        listAbastecimiento = abastecimientoDAO.getListAbastecimientoSolicitudes();

        changeView1();
    }

    public void actualizarInventario() {
        LocalHasInsumoDAO localHasInsumoDAO = new LocalHasInsumoDAO();
        for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumo) {
            LocalHasInsumo localHasInsumo = localHasInsumoDAO.getLocalHasInsumo(item.getInsumo());
            if (localHasInsumo.getCantidad() < item.getCantidad()) {
                map.put("messageFormAbastecimientoDetail", "No tiene suficientes insumos en almacen, tendra que postergar la solicitud");
                map.put("messageTypeFormAbastecimientoDetail", "danger");
                return;
            }
        }

        for (AbastecimientoHasInsumo item : listAbastecimientoHasInsumo) {
            LocalHasInsumo localHasInsumo = localHasInsumoDAO.getLocalHasInsumo(item.getInsumo());
            localHasInsumo.setCantidad(localHasInsumo.getCantidad() - item.getCantidad());
            localHasInsumoDAO.update(localHasInsumo);
        }
    }

    public void getListAbastecimientoHasInsumo(Abastecimiento a) {
        abastecimiento = a;
        abastecimientoHasInsumoDAO = new AbastecimientoHasInsumoDAO();
        listAbastecimientoHasInsumo = abastecimientoHasInsumoDAO.getListAbastecimientoHasInsumo(abastecimiento);

        estadoAbastecimientoDAO = new EstadoAbastecimientoDAO();
        listEstadoAbastecimiento = estadoAbastecimientoDAO.getListEstadoAbastecimiento();
    }

    public void changeView1() {
        idDiv = 1;
    }

    public void changeView2() {
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

    public List<AbastecimientoHasInsumo> getListAbastecimientoHasInsumo() {
        return listAbastecimientoHasInsumo;
    }

    public void setListAbastecimientoHasInsumo(List<AbastecimientoHasInsumo> listAbastecimientoHasInsumo) {
        this.listAbastecimientoHasInsumo = listAbastecimientoHasInsumo;
    }

    public List<EstadoAbastecimiento> getListEstadoAbastecimiento() {
        return listEstadoAbastecimiento;
    }

    public void setListEstadoAbastecimiento(List<EstadoAbastecimiento> listEstadoAbastecimiento) {
        this.listEstadoAbastecimiento = listEstadoAbastecimiento;
    }

    public int getEstadoAbastecimientoID() {
        return estadoAbastecimientoID;
    }

    public void setEstadoAbastecimientoID(int estadoAbastecimientoID) {
        this.estadoAbastecimientoID = estadoAbastecimientoID;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public int getIdDiv() {
        return idDiv;
    }

    public void setIdDiv(int idDiv) {
        this.idDiv = idDiv;
    }
}
