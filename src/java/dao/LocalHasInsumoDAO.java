package dao;

import java.io.Serializable;
import java.util.List;
import model.LocalHasInsumo;
import org.hibernate.Query;
import org.hibernate.Session;

public class LocalHasInsumoDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<LocalHasInsumo> getListLocalHasInsumo() {
        List<LocalHasInsumo> listLocalHasInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from LocalHasInsumo lhi inner join fetch lhi.local l inner join fetch lhi.insumo i inner join fetch i.unidadMedida um");
            listLocalHasInsumo = (List<LocalHasInsumo>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listLocalHasInsumo;
    }
}