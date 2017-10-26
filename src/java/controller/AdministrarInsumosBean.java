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

@Named(value = "administrarInsumosBean")
@ViewScoped
public class AdministrarInsumosBean implements Serializable {

    private InsumoDAO insumoDAO;

    private Insumo insumo;

    private PresentacionInsumoDAO presentacionInsumoDAO;
    private PresentacionInsumo presentacionInsumo;
    private List<PresentacionInsumo> listPresentacionInsumo;
    private List<PresentacionInsumo> listPresentacionInsumoRegistrar;

    private UnidadMedida unidadMedida;
    private List<UnidadMedida> listUnidadMedida;
    private int unidadMedidaID;

    private int idDiv;

    private Map<String, String> map;

    public AdministrarInsumosBean() {
        map = new HashMap<>();
        insumoDAO = new InsumoDAO();
        
        insumo = new Insumo();
        
        presentacionInsumoDAO = new PresentacionInsumoDAO();

        presentacionInsumo = new PresentacionInsumo();
        listPresentacionInsumoRegistrar = new ArrayList<>();
        
        idDiv = 1;
    }

    public void addPresentacionInsumo() {
        System.out.println(presentacionInsumo.getNombre());
        if (presentacionInsumo.getNombre().equals("xxx")) {
            map.put("nombre", "error nombre");
        }
        presentacionInsumo.setInsumo(insumo);
        listPresentacionInsumoRegistrar.add(presentacionInsumo);
        presentacionInsumo = new PresentacionInsumo();
    }
    
    public void saveInsumo() {
        for (UnidadMedida item : listUnidadMedida) {
            if (unidadMedidaID == item.getId()) {
                unidadMedida = item;
                break;
            }
        }
        insumo.setUnidadMedida(unidadMedida);
        insumo.setEstado('A');
        insumoDAO.create(insumo);
        for (PresentacionInsumo item : listPresentacionInsumoRegistrar) {
            item.setEstado('A');
            presentacionInsumoDAO.create(item);
        }
        unidadMedidaID = 0;
        insumo = new Insumo();
        listPresentacionInsumoRegistrar.clear();
    }

    public void changeViewIndex() {
        idDiv = 1;
    }

    public void changeViewRegistrarInsumo() {
        idDiv = 2;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public PresentacionInsumo getPresentacionInsumo() {
        return presentacionInsumo;
    }

    public void setPresentacionInsumo(PresentacionInsumo presentacionInsumo) {
        this.presentacionInsumo = presentacionInsumo;
    }

    public List<PresentacionInsumo> getListPresentacionInsumo() {
        if (listPresentacionInsumo == null || listPresentacionInsumo.isEmpty()) {
            listPresentacionInsumo = presentacionInsumoDAO.getListPresentacionInsumo();
        }
        return listPresentacionInsumo;
    }

    public void setListPresentacionInsumo(List<PresentacionInsumo> listPresentacionInsumo) {
        this.listPresentacionInsumo = listPresentacionInsumo;
    }

    public List<PresentacionInsumo> getListPresentacionInsumoRegistrar() {
        return listPresentacionInsumoRegistrar;
    }

    public void setListPresentacionInsumoRegistrar(List<PresentacionInsumo> listPresentacionInsumoRegistrar) {
        this.listPresentacionInsumoRegistrar = listPresentacionInsumoRegistrar;
    }

    public List<UnidadMedida> getListUnidadMedida() {
        if (listUnidadMedida == null || listUnidadMedida.isEmpty()) {
            listUnidadMedida = UnidadMedidaDAO.getListUnidadMedida();
        }
        return listUnidadMedida;
    }

    public void setListUnidadMedida(List<UnidadMedida> listUnidadMedida) {
        this.listUnidadMedida = listUnidadMedida;
    }

    public int getUnidadMedidaID() {
        return unidadMedidaID;
    }

    public void setUnidadMedidaID(int unidadMedidaID) {
        this.unidadMedidaID = unidadMedidaID;
    }

    public int getIdDiv() {
        return idDiv;
    }

    public void setIdDiv(int idDiv) {
        this.idDiv = idDiv;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
