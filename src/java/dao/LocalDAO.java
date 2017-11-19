package dao;

import java.io.Serializable;
import java.util.List;
import model.*;
import org.hibernate.Query;
import org.hibernate.Session;

public class LocalDAO extends ADAO_crud<Object> implements Serializable {

    public List<Local> getListLocalDestinoAbastecimiento(Local localOrigen) {
        List<Local> listLocal = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Local l where l.id <> :localID");
            query.setParameter("localID", localOrigen.getId());
            listLocal = (List<Local>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listLocal;
    }
}
