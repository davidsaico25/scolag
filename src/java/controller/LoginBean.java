package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import model.Modulo;
import model.Perfil;
import model.Usuario;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private Usuario usuario;

    private boolean logged = false;

    private Map<String, String> map;

    public LoginBean() {
        usuario = new Usuario();
        map = new HashMap<>();
    }

    public String login() {
        String outcome = "";
        if (usuario.getUsername().equals("davisonsp")) {
            if (usuario.getPassword().equals("123")) {
                logged = true;
                List<Modulo> listModulo = new ArrayList<>();
                listModulo.add(new Modulo("Registrar Entrada Insumos", "registrarEntradaInsumos"));
                listModulo.add(new Modulo("Registrar Salida Insumos", "registrarSalidaInsumos"));
                Perfil perfil = new Perfil("jefe de almacen", "almacen", listModulo);
                usuario.setPerfil(perfil);
                outcome = "intranet";
            } else {
                map.put("password", "password incorrecto");
                logged = false;
                outcome = "";
            }
        } else {
            map.put("username", "username incorrecto");
            logged = false;
            outcome = "index";
        }
        return outcome;
    }

    public String logout() {
        logged = false;
        usuario = new Usuario();
        /*
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
         */
        return "login";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
