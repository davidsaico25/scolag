package controller;

import dao.PersonaDAO;
import dao.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Persona;
import model.Usuario;

@Named(value = "adminUsuariosBean")
@ViewScoped
public class AdminUsuariosBean implements Serializable {
    
    private List<Usuario> listUsuario;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    
    private Persona persona;
    private PersonaDAO personaDAO;

    public AdminUsuariosBean() {
        usuarioDAO = new UsuarioDAO();
        personaDAO = new PersonaDAO();
    }
    
    public void crearUsuario() {
        usuarioDAO.create(usuario);
        setListUsuario(usuarioDAO.getListUsuario());
        usuario = new Usuario();
        persona = new Persona();
    }
    
    public void actualizarUsuario() {
        usuarioDAO.update(usuario);
        usuario = new Usuario();
    }
    
    public void resetUsuario() {
        if(usuario != null) {
            usuario = new Usuario();
            persona = new Persona();
        }
    }
    
    public List<Usuario> getListUsuario() {
        if (listUsuario == null) {
            setListUsuario(usuarioDAO.getListUsuario());
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
