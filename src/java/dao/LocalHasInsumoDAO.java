package dao;

import java.io.Serializable;
import java.util.List;
import model.Insumo;
import model.Local;
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
    
    public List<LocalHasInsumo> getListLocalHasInsumo(Local local) {
        List<LocalHasInsumo> listLocalHasInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from LocalHasInsumo lhi inner join fetch lhi.local l inner join fetch lhi.insumo i inner join fetch i.unidadMedida um where l.id= :localID");
            query.setParameter("localID", local.getId());
            listLocalHasInsumo = (List<LocalHasInsumo>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listLocalHasInsumo;
    }
    
    public LocalHasInsumo getLocalHasInsumo(Local local, Insumo insumo) {
        LocalHasInsumo localHasInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from LocalHasInsumo lhi inner join fetch lhi.insumo i inner join fetch lhi.local l where i.id= :insumoID and l.id= :localID");
            query.setParameter("localID", local.getId());
            query.setParameter("insumoID", insumo.getId());
            localHasInsumo = (LocalHasInsumo) query.uniqueResult();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return localHasInsumo;
    }
}