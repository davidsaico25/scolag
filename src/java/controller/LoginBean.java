package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
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
            /*
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().getSessionMap().put("usuario", usuario);
            */
            outcome = "intranet";
        } else {
            logged = false;
            outcome = "error";
        }
        return outcome;
    }
    
    public String logout() {
        logged = false;
        /*
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        */
        return "index";
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