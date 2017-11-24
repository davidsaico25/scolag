package dao;

import java.io.Serializable;
import java.util.List;
import model.Insumo;
import model.PresentacionInsumo;
import org.hibernate.Query;
import org.hibernate.Session;

public class PresentacionInsumoDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<PresentacionInsumo> getListPresentacionInsumo() {
        List<PresentacionInsumo> listPresentacionInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from PresentacionInsumo pi inner join fetch pi.insumo i inner join fetch i.unidadMedida um where pi.estado='A' and i.estado='A'");
            listPresentacionInsumo = (List<PresentacionInsumo>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listPresentacionInsumo;
    }
    
    public List<PresentacionInsumo> getListPresentacionInsumo(Insumo insumo) {
        List<PresentacionInsumo> listPresentacionInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from PresentacionInsumo pi inner join fetch pi.insumo i inner join fetch i.unidadMedida um where pi.estado='A' and i.estado='A' and i.id=:insumoID");
            query.setParameter("insumoID", insumo.getId());
            listPresentacionInsumo = (List<PresentacionInsumo>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listPresentacionInsumo;
    }
    
    public PresentacionInsumo getPresentacionInsumoByCodBarra(String codBarra) {
        PresentacionInsumo presentacionInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from PresentacionInsumo pi where pi.codigoBarra = :codBarra");
            query.setParameter("codBarra", codBarra);
            presentacionInsumo = (PresentacionInsumo) query.uniqueResult();
            return presentacionInsumo;
        } catch(Exception e) {
        }finally {
            if (session != null) {
                session.close();
            }
        }
        
        return null;
    }
}
