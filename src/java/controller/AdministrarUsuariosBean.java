package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Modulo;
import model.Perfil;
import model.Persona;
import model.Usuario;

@Named(value = "administrarUsuariosBean")
@ViewScoped
public class AdministrarUsuariosBean implements Serializable {

    private List<Usuario> listUsuario;
    private Usuario usuario;
    
    public AdministrarUsuariosBean() {
        listUsuario = new ArrayList<>();
        Persona persona  = new Persona("david", "saico", "paucar", "M", "25-11-1992");
        List<Modulo> listModulo = new ArrayList<>();
        listModulo.add(new Modulo("Administrar Usuarios", "administrarUsuarios"));
        Perfil perfil = new Perfil("jefe de almacen", listModulo);
        Usuario usuario = new Usuario("davisonsp", "123", "davidsaico25@outlook.com", perfil);
        usuario.setPersona(persona);
        listUsuario.add(usuario);
    }

    public List<Usuario> getListUsuario() {
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