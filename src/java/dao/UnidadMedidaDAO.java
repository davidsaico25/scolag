package dao;

import java.io.Serializable;
import java.util.List;
import model.UnidadMedida;
import org.hibernate.Query;
import org.hibernate.Session;

public class UnidadMedidaDAO extends ADAO_crud<Object> implements Serializable {
    
    public static List<UnidadMedida> getListUnidadMedida() {
        List<UnidadMedida> listUnidadMedida = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from UnidadMedida");
            listUnidadMedida = (List<UnidadMedida>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listUnidadMedida;
    }
}