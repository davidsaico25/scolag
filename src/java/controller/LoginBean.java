package controller;

import dao.PerfilHasSubmenuDAO;
import dao.UsuarioDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Perfil;
import model.Submenu;
import model.Usuario;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private UsuarioDAO usuarioDAO;

    private Usuario usuario;
    
    private List<String> listMenu;
    private List<Submenu> listSubmenu;

    private boolean logged = false;

    private Map<String, String> map;

    private HttpSession httpSession;

    public LoginBean() {
        usuarioDAO = new UsuarioDAO();
        usuario = new Usuario();
    }

    public String login() {
        map = new HashMap<>();
        String outcome = "";
        usuario = usuarioDAO.login(usuario);
        if (usuario != null) {
            System.out.println("Login Bean:");
            System.out.println(usuario.getUsername() + " - " + usuario.getPassword());
            Perfil perfil = usuario.getPerfil();
            listMenu = new ArrayList<>();
            listSubmenu = PerfilHasSubmenuDAO.getListSubmenu(perfil);
            for (Submenu submenu : listSubmenu) {
                if (!listMenu.contains(submenu.getMenu().getNombre())) {
                    listMenu.add(submenu.getMenu().getNombre());
                }
            }
            
            logged = true;
            httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.setAttribute("usuario", this.usuario);
            httpSession.setAttribute("logged", this.logged);
            outcome = "user";
        } else {
            map.put("msg", "credenciales incorrectas");
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

    public List<String> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<String> listMenu) {
        this.listMenu = listMenu;
    }

    public List<Submenu> getListSubmenu() {
        return listSubmenu;
    }

    public void setListSubmenu(List<Submenu> listSubmenu) {
        this.listSubmenu = listSubmenu;
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
}
