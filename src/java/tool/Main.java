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
            List<Usuario> listUsuario = daou.getListUsuario();
            for (Usuario usuario : listUsuario) {
                System.out.println(usuario.getUsername());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.exit(0);
        }
    }

}
