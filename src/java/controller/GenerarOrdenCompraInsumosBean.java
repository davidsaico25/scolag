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

@Named(value = "generarOrdenCompraInsumosBean")
@ViewScoped
public class GenerarOrdenCompraInsumosBean implements Serializable {

    private PresentacionInsumoDAO presentacionInsumoDAO;
    private PresentacionInsumo presentacionInsumo;
    List<PresentacionInsumo> listPresentacionInsumos;
    List<PresentacionInsumo> listCarritoPresentacionInsumos;
    
    
    private LocalHasInsumoDAO localHasInsumoDAO;
    private LocalHasInsumo localHasInsumo;
    private List<LocalHasInsumo> listLocalHasInsumo;
    private List<LocalHasInsumo> listLocalHasInsumoActualizar;
    
    private OrdenCompraHasPresentacionInsumo ordenCompraHasPresentacionInsumo;
    private OrdenCompraHasPresentacionInsumoDAO ordenCompraHasPresentacionInsumoDAO;

    private List<OrdenCompraHasPresentacionInsumo> listOrdenCompraHasPresentacionInsumo;

    private OrdenCompraDAO ordenCompraDAO;
    private OrdenCompra ordenCompra;
 
    private ProveedorDAO proveedorDAO;
    private Proveedor proveedor;
    List<Proveedor> listProveedor;
    
    private int cantidad;
    private double PrecioCosto;
    private double PrecioT;
        
    private Map<String, String> map;

    public GenerarOrdenCompraInsumosBean() {

        cantidad = 0;

        PrecioT = 0;

        map = new HashMap<>();

        presentacionInsumoDAO = new PresentacionInsumoDAO();
        
        ordenCompraHasPresentacionInsumoDAO = new OrdenCompraHasPresentacionInsumoDAO();

        listPresentacionInsumos = presentacionInsumoDAO.getListPresentacionInsumo();

        listCarritoPresentacionInsumos = new ArrayList<>();

        ordenCompraHasPresentacionInsumo = new OrdenCompraHasPresentacionInsumo();

        listOrdenCompraHasPresentacionInsumo = new ArrayList<>();

        ordenCompra = new OrdenCompra();
        
        proveedorDAO = new ProveedorDAO();
        listProveedor = proveedorDAO.getListProveedor();
        
        localHasInsumoDAO = new LocalHasInsumoDAO();
                 
        listLocalHasInsumo = localHasInsumoDAO.getListLocalHasInsumo();
       
        listLocalHasInsumoActualizar = new ArrayList<>();

    }
    
    public void modalOK(PresentacionInsumo pi) {
        //System.out.println(ordenCompraHasPresentacionInsumo.getCantidad());
        ordenCompraHasPresentacionInsumo.setPresentacionInsumo(pi);
        ordenCompraHasPresentacionInsumo.setPrecioUnitario(pi.getPrecioCosto());
    }
    
    public void guardarOrdenCompraInsumos() {
        if (proveedor == null || proveedor.getId() == null) {
            map.put("messageFormCompra", "Tiene que seleccionar un proveedor");
            map.put("messageTypeFormCompra", "danger");
            return;
        }
        System.out.println(PrecioT);
        System.out.println(proveedor.getNombre());
        
        ordenCompra.setMontoTotal(PrecioT);
        ordenCompra.setProveedor(proveedor);
        EstadoOrdenCompra estadoOrdenCompra = new EstadoOrdenCompra();
        estadoOrdenCompra.setId(1);
        ordenCompra.setEstadoOrdenCompra(estadoOrdenCompra);
        
        ordenCompraDAO = new OrdenCompraDAO();
        ordenCompraDAO.create(ordenCompra);
        
        OrdenCompraHasPresentacionInsumoId ordenCompraHasPresentacionInsumoId = new OrdenCompraHasPresentacionInsumoId();
        ordenCompraHasPresentacionInsumoId.setOrdenCompraId(ordenCompra.getId());
        
        OrdenCompraHasPresentacionInsumoDAO ordenCompraHasPresentacionInsumoDAO = new OrdenCompraHasPresentacionInsumoDAO();
        for (OrdenCompraHasPresentacionInsumo item : listOrdenCompraHasPresentacionInsumo) {
            ordenCompraHasPresentacionInsumoId.setPresentacionInsumoId(item.getPresentacionInsumo().getId());
            item.setId(ordenCompraHasPresentacionInsumoId);
            item.setOrdenCompra(ordenCompra);
            ordenCompraHasPresentacionInsumoDAO.create(item);
        }
        
        listOrdenCompraHasPresentacionInsumo = new ArrayList<>();
        PrecioT = 0;
        cantidad = 0;
        proveedor = new Proveedor();
        cleanMap();
    }
    
    public void editar(OrdenCompraHasPresentacionInsumo rhpi){
                       
        ordenCompraHasPresentacionInsumo.setPrecioUnitario(rhpi.getPrecioUnitario());
      
        ordenCompraHasPresentacionInsumo.setCantidad(rhpi.getCantidad());
        
        ordenCompraHasPresentacionInsumo.setPresentacionInsumo(rhpi.getPresentacionInsumo());
        
        listOrdenCompraHasPresentacionInsumo.remove(rhpi);
        
        costototal();
        
    }
   public void validarCantidad(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Integer number = (Integer) value;
        String summary = "Error Cantidad minima";
        String detail = "";
        if (number == null) {
            detail = "No se permite campo vacio";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (number == 0) {
            detail = "La cantidad debe ser mayor a 0";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (number < 0) {
            detail = "La cantidad debe ser un valor positivo";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
         if (!RegexValidation.onlyNumbers(String.valueOf(number))) {
            detail = "Formato incorrecto";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
   public void validarPrecioUnitario(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Double number = (Double) value;
        String summary = "Error Cantidad minima";
        String detail = "";
        if (number == null) {
            detail = "No se permite campo vacio";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (number == 0) {
            detail = "El precio unitario debe ser mayor a 0.0";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (!RegexValidation.isDecimalNumber(String.valueOf(number))) {
            detail = "Formato incorrecto";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        if (number < 0) {
            detail = "El precio debe ser un valor positivo";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
   
//   public void costototal(listOrdenCompraHasPresentacionInsumo lrhpi){
//       System.out.println(listOrdenCompraHasPresentacionInsumo.get(3));
//       ordenCompraHasPresentacionInsumo.getCantidad();
//       ordenCompraHasPresentacionInsumo.getPrecioUnitario();
//   }
    
    public void limpiar(){
        listOrdenCompraHasPresentacionInsumo.clear();
        costototal();
    }
    
    public void eliminarPresentacionInsumoCarrito(OrdenCompraHasPresentacionInsumo rhpi) {
        listOrdenCompraHasPresentacionInsumo.remove(rhpi);
        costototal();
    }
public void costototal(){
      double total = 0;
        for(int i=0; i<listOrdenCompraHasPresentacionInsumo.size(); i++) {
            OrdenCompraHasPresentacionInsumo temp = listOrdenCompraHasPresentacionInsumo.get(i);
            total+=temp.getPrecioUnitario()*temp.getCantidad();
        }
         setPrecioT(total);
}
    public void agregarPresentacionInsumoCarrito() {
        if (ordenCompraHasPresentacionInsumo.getPresentacionInsumo() == null) {
            map.put("messageFormCompra", "Tiene que seleccionar una presentacion de insumo");
            map.put("messageTypeFormCompra", "danger");
            return;
        }
      listOrdenCompraHasPresentacionInsumo.add(ordenCompraHasPresentacionInsumo);
        cleanMap();
        ordenCompraHasPresentacionInsumo = new OrdenCompraHasPresentacionInsumo();

      costototal();
   
    }
    
    public void cleanMap() {
        map.clear();
    }

    public List<PresentacionInsumo> getListPresentacionInsumos() {
        return listPresentacionInsumos;
    }

    public void setListPresentacionInsumos(List<PresentacionInsumo> listPresentacionInsumos) {
        this.listPresentacionInsumos = listPresentacionInsumos;
    }

    public PresentacionInsumo getPresentacionInsumo() {
        return presentacionInsumo;
    }

    public void setPresentacionInsumo(PresentacionInsumo presentacionInsumo) {
        this.presentacionInsumo = presentacionInsumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<PresentacionInsumo> getListCarritoPresentacionInsumos() {
        return listCarritoPresentacionInsumos;
    }

    public void setListCarritoPresentacionInsumos(List<PresentacionInsumo> listCarritoPresentacionInsumos) {
        this.listCarritoPresentacionInsumos = listCarritoPresentacionInsumos;
    }

    public double getPrecioCosto() {
        return PrecioCosto;
    }

    public void setPrecioCosto(double PrecioCosto) {
        this.PrecioCosto = PrecioCosto;
    }

    public double getPrecioT() {
        return PrecioT;
    }

    public void setPrecioT(double PrecioT) {
        this.PrecioT = PrecioT;
    }

    public OrdenCompraHasPresentacionInsumo getOrdenCompraHasPresentacionInsumo() {
        return ordenCompraHasPresentacionInsumo;
    }

    public void setOrdenCompraHasPresentacionInsumo(OrdenCompraHasPresentacionInsumo ordenCompraHasPresentacionInsumo) {
        this.ordenCompraHasPresentacionInsumo = ordenCompraHasPresentacionInsumo;
    }

    public List<OrdenCompraHasPresentacionInsumo> getListOrdenCompraHasPresentacionInsumo() {
        return listOrdenCompraHasPresentacionInsumo;
    }

    public void setListOrdenCompraHasPresentacionInsumo(List<OrdenCompraHasPresentacionInsumo> listOrdenCompraHasPresentacionInsumo) {
        this.listOrdenCompraHasPresentacionInsumo = listOrdenCompraHasPresentacionInsumo;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getListProveedor() {
        return listProveedor;
    }

    public void setListProveedor(List<Proveedor> listProveedor) {
        this.listProveedor = listProveedor;
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

    public List<LocalHasInsumo> getListLocalHasInsumoActualizar() {
        return listLocalHasInsumoActualizar;
    }

    public void setListLocalHasInsumoActualizar(List<LocalHasInsumo> listLocalHasInsumoActualizar) {
        this.listLocalHasInsumoActualizar = listLocalHasInsumoActualizar;
    }

    
    
}
