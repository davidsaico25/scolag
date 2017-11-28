package controller;

import dao.LocalHasInsumoDAO;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.LocalHasInsumo;
import model.Usuario;

@Named(value = "dashBoardBean")
@Dependent
public class DashBoardBean implements Serializable {

    private LocalHasInsumoDAO localHasInsumoDAO;
    private List<LocalHasInsumo> listLocalHasInsumo;

    private Usuario usuario;

    public DashBoardBean() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usuario = (Usuario) httpSession.getAttribute("usuario");
        
        localHasInsumoDAO = new LocalHasInsumoDAO();
        listLocalHasInsumo = localHasInsumoDAO.getListLocalHasInsumo(usuario.getLocal());
    }

    public List<LocalHasInsumo> getListLocalHasInsumo() {
        return listLocalHasInsumo;
    }

    public void setListLocalHasInsumo(List<LocalHasInsumo> listLocalHasInsumo) {
        this.listLocalHasInsumo = listLocalHasInsumo;
    }
}
