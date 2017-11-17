package dao;

import java.io.Serializable;
import java.util.List;
import model.Insumo;
import org.hibernate.Query;
import org.hibernate.Session;

public class InsumoDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<Insumo> getListInsumo() {
        List<Insumo> listInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Insumo i inner join fetch i.unidadMedida um where i.estado='A'");
            listInsumo = (List<Insumo>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listInsumo;
    }
}