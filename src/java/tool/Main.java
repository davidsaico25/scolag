
package tool;

import dao.UsuarioDAO;
import java.util.List;
import model.Usuario;

public class Main {

    public static void main(String[] args) {
        UsuarioDAO daoUsuario = new UsuarioDAO();
        List<Usuario> listUsuario = daoUsuario.getListUsuario();
        
        for (Usuario usuario : listUsuario) {
            System.out.println(usuario.getEmail());
        }
        System.exit(0);
    }
    
}
