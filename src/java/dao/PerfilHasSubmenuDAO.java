package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Perfil;
import model.PerfilHasSubmenu;
import model.Submenu;
import org.hibernate.Query;
import org.hibernate.Session;

public class PerfilHasSubmenuDAO extends ADAO_crud<Object> implements Serializable {
    
    public static List<Submenu> getListSubmenu(Perfil perfil) {
        List<Submenu> listSubmenu = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from PerfilHasSubmenu phsm inner join fetch phsm.perfil p inner join fetch phsm.submenu sm inner join fetch sm.menu m where p.id = :perfil_id");
            query.setParameter("perfil_id", perfil.getId());
            List<PerfilHasSubmenu> list = query.list();
            for (PerfilHasSubmenu perfilHasSubmenu : list) {
                listSubmenu.add(perfilHasSubmenu.getSubmenu());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }return listSubmenu;
    }
}
