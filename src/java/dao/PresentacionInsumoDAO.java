package dao;

import java.io.Serializable;
import java.util.List;
import model.PresentacionInsumo;
import org.hibernate.Query;
import org.hibernate.Session;

public class PresentacionInsumoDAO extends ADAO_crud<Object> implements Serializable {
    
    public static List<PresentacionInsumo> getListPresentacionInsumo() {
        List<PresentacionInsumo> listPresentacionInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from PresentacionInsumo pi inner join fetch pi.insumo i inner join fetch i.unidadMedida um");
            listPresentacionInsumo = (List<PresentacionInsumo>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listPresentacionInsumo;
    }
}
