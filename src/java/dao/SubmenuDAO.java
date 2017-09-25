package dao;

import java.io.Serializable;
import java.util.List;
import model.Perfil;
import org.hibernate.Query;
import org.hibernate.Session;

public class SubmenuDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<SubmenuDAO> getListSubmenu(Perfil perfil) {
        List<SubmenuDAO> listMenu = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Submenu sm inner join fetch sm.perfilHasSubmenus phsm inner join fetch sm.menu m where phsm.perfil.id = 2");
            listMenu = (List<SubmenuDAO>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listMenu;
    }
}
