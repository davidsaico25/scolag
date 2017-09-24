package controller;

import dao.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Persona;
import model.Usuario;

@Named(value = "adminUsuariosBean")
@ViewScoped
public class AdminUsuariosBean implements Serializable {
    
    private List<Usuario> listUsuario;
    private UsuarioDAO dao;
    private Usuario usuario;

    public AdminUsuariosBean() {
        dao = new UsuarioDAO();
    }
    
    public void crearUsuario(AjaxBehaviorEvent event) {
        dao.create(usuario);
        setListUsuario(dao.getListUsuario());
    }
    
    public void actualizarUsuario() {
        dao.update(usuario);
    }
    
    public List<Usuario> getListUsuario() {
        if (listUsuario == null) {
            setListUsuario(dao.getListUsuario());
        }
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
