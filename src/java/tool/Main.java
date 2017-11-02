package tool;

import dao.*;
import java.util.List;
import model.*;

public class Main {

    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.setUsername("user3");
        LocalDAO dao = new LocalDAO();
        
        List<Local> listLocal = dao.getListLocalAbastecimiento(usuario);
        
        for (Local item : listLocal) {
            System.out.println(item.getNombre());
        }
        
        System.exit(0);
    }
}