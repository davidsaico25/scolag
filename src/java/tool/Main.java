package tool;

import dao.PerfilHasSubmenuDAO;
import dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import model.*;

public class Main {

    public static void main(String[] args) {
        try {
            UsuarioDAO daou = new UsuarioDAO();
            Usuario usuario1 = new Usuario();
            usuario1.setUsername("davisonsp");
            usuario1.setPassword("123");
            Usuario usuario = daou.login(usuario1);
            
            Perfil perfil = usuario.getPerfil();
            perfil.setId(perfil.getId());
            List<Submenu> list = PerfilHasSubmenuDAO.getListSubmenu(perfil);
            for (Submenu submenu : list) {
                System.out.println(submenu.getMenu().getNombre());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.exit(0);
        }
    }

}
