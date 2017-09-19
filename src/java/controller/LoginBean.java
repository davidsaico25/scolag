package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import model.Modulo;
import model.Perfil;
import model.Usuario;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private Usuario usuario;
    
    private boolean logged = false;
    
    public LoginBean() {
        usuario = new Usuario();
    }
    
    public String login() {
        String outcome = "";
        if (usuario.getUsername().equals("davisonsp") && usuario.getPassword().equals("123")) {
            logged = true;
            List<Modulo> listModulo = new ArrayList<>();
            listModulo.add(new Modulo("Registrar Entrada Insumos", "registrarEntradaInsumos"));
            listModulo.add(new Modulo("Registrar Salida Insumos", "registrarSalidaInsumos"));
            Perfil perfil = new Perfil("jefe de almacen", "almacen", listModulo);
            usuario.setPerfil(perfil);
            /*
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().getSessionMap().put("usuario", usuario);
            */
            outcome = "intranet";
        } else {
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
}