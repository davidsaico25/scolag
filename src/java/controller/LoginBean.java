package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Modulo;
import model.Perfil;
import model.Usuario;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private Usuario usuario;
    
    List<Modulo> listModulo;

    private boolean logged = false;

    private Map<String, String> map;
    
    private HttpSession httpSession;

    public LoginBean() {
        usuario = new Usuario();
        listModulo = new ArrayList<>();
    }

    public String login() {
        map = new HashMap<>();
        String outcome = "";
        if (usuario.getUsername().equals("davisonsp")) {
            if (usuario.getPassword().equals("123")) {
                logged = true;
                Perfil perfil = new Perfil("almacen", 'A');
                listModulo.add(new Modulo(perfil, "Registrar Entrada Insumos", "registrarEntradaInsumos", 'A'));
                listModulo.add(new Modulo(perfil, "Registrar Salida Insumos", "registrarSalidaInsumos", 'A'));
                listModulo.add(new Modulo(perfil, "Administrar Usuarios", "administrarUsuarios", 'A'));
                usuario.setPerfil(perfil);
                httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                httpSession.setAttribute("usuario", this.usuario);
                httpSession.setAttribute("logged", this.logged);
                outcome = perfil.getNombre();
            } else {
                map.put("password", "password incorrecto");
                logged = false;
                outcome = "";
            }
        } else {
            map.put("username", "username incorrecto");
            logged = false;
        }
        return outcome;
    }

    public String logout() {
        httpSession.removeAttribute("usuario");
        httpSession.removeAttribute("menuModel");
        httpSession.invalidate();
        logged = false;
        usuario = new Usuario();
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
    public HttpSession getHttpSession() {
        return httpSession;
    }

    public List<Modulo> getListModulo() {
        return listModulo;
    }

    public void setListModulo(List<Modulo> listModulo) {
        this.listModulo = listModulo;
    }
    
}
