package dao;

import java.io.Serializable;
import java.util.List;
import model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LocalDAO extends ADAO_crud<Object> implements Serializable {

    public List<Local> getListLocal() {
        List<Local> listLocal = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Local");
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
