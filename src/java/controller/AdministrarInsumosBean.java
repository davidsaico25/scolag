package controller;

import dao.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.*;
import tool.RegexValidation;

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
        presentacionInsumo.setInsumo(insumo);
        listPresentacionInsumoRegistrar.add(presentacionInsumo);
        map.put("messageFormRegistrarPresentacionInsumo", "Presentacion " + presentacionInsumo.getNombre() + " agregada");
        map.put("messageTypeFormRegistrarPresentacionInsumo", "success");
        presentacionInsumo = new PresentacionInsumo();
    }

    public void saveInsumo() {
        if (listPresentacionInsumoRegistrar.isEmpty()) {
            map.put("messageFormRegistrarInsumo", "Debe registrar al menos una presentacion de insumo");
            map.put("messageTypeFormRegistrarInsumo", "danger");
            return;
        }
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
        listPresentacionInsumoRegistrar.clear();
        map.put("messageFormRegistrarInsumo", "Insumo " + insumo.getNombre() + " agregada");
        map.put("messageTypeFormRegistrarInsumo", "success");
        insumo = new Insumo();
    }

    public void cleanMap() {
        map.clear();
    }

    public void validateNombreInsumo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String text = (String) value;
        String summary = "Error nombre insumo";
        String detail = "";
        if (text.length() == 0) {
            detail = "No se permite campo vacio";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (!RegexValidation.onlyLetters(text)) {
            detail = "Solo se permite letras";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }

    public void validateDescripcionInsumo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String text = (String) value;
        String summary = "Error descripcion insumo";
        String detail = "";
        if (text.length() == 0) {
            return;
        }
        if (!RegexValidation.onlyLetters(text)) {
            detail = "Solo se permite letras";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }

    public void validateStockMinimo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Integer number = (Integer) value;
        String summary = "Error stock minimo";
        String detail = "";
        if (number == null) {
            detail = "No se permite campo vacio";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (number == 0) {
            detail = "Se requiere este campo";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (number < 0) {
            detail = "debe ser mayor que cero";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }

    public void validateNombrePresentacionInsumo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String text = (String) value;
        String summary = "Error nombre insumo";
        String detail = "";
        if (text.length() == 0) {
            detail = "No se permite campo vacio";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (!RegexValidation.onlyLetters(text)) {
            detail = "Solo se permite letras";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }

    public void validateDescripcionPresentacionInsumo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String text = (String) value;
        String summary = "Error descripcion insumo";
        String detail = "";
        if (text.length() == 0) {
            detail = "No se permite campo vacio";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (!RegexValidation.onlyLetters(text)) {
            detail = "Solo se permite letras";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }

    public void validateRendimiento(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Double number = (Double) value;
        String summary = "Error rendimiento";
        String detail = "";
        if (number == null) {
            detail = "No se permite campo vacio";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (number == 0) {
            detail = "Se requiere este campo";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (number < 0) {
            detail = "debe ser mayor que cero";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }

    public void validatePrecioCosto(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Double number = (Double) value;
        String summary = "Error precio costo";
        String detail = "";
        if (number == null) {
            detail = "No se permite campo vacio";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (number == 0) {
            detail = "Se requeire este campo";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (number < 0) {
            detail = "debe ser mayor que cero";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (!RegexValidation.isDecimalNumber(String.valueOf(number))) {
            detail = "Formato incorrecto";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
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
