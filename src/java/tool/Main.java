
package tool;

import dao.DAOUsuario;
import java.util.List;
import model.Usuario;

public class Main {

    public static void main(String[] args) {
        DAOUsuario daoUsuario = new DAOUsuario();
        List<Usuario> listUsuario = daoUsuario.getListUsuario();
        
        for (Usuario usuario : listUsuario) {
            System.out.println(usuario.getEmail());
        }
        System.exit(0);
    }
    
}
