package controller;

import dao.DAOUsuario;
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
    DAOUsuario daoUsuario;
    private Usuario usuario;
    
    public AdministrarUsuariosBean() {
        usuario = new Usuario();
        listUsuario = null;
        System.out.println("entro construct");
        //listUsuario = daoUsuario.getListUsuario();
        /*
        listUsuario = new ArrayList<>();
        Persona persona  = new Persona("david", "saico", "paucar", "M", "25-11-1992");
        Perfil perfil = new Perfil("jefe de almacen", 'A');
        List<Modulo> listModulo = new ArrayList<>();
        listModulo.add(new Modulo(perfil, "Administrar Usuarios", "administrarUsuarios", 'A'));
        
        Usuario usuario = new Usuario(perfil, persona, "davisonsp", "123", "davidsaico25@outlook.com", 'A');
        listUsuario.add(usuario);*/
        
    }

    public List<Usuario> getListUsuario() {
        System.out.println("entro getListUsuario");
        daoUsuario = new DAOUsuario();
        listUsuario = daoUsuario.getListUsuario();
        for (Usuario usuariox : listUsuario) {
            System.out.println(usuariox.getEmail());
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