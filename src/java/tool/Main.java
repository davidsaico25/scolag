package tool;

import dao.*;
import java.util.List;
import model.*;

public class Main {

    public static void main(String[] args) {
        PresentacionInsumo presentacionInsumo = null;
        PresentacionInsumoDAO presentacionInsumoDAO = new PresentacionInsumoDAO();
        presentacionInsumo = presentacionInsumoDAO.getPresentacionInsumoByCodBarra("1234567890123");
        if (presentacionInsumo == null) {
            System.out.println("pi NO existe");
        } else {
            System.out.println("pi SI existe");
        }
        
        System.exit(0);
    }
}