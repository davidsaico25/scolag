package dao;

import java.io.Serializable;
import java.util.List;
import model.Abastecimiento;
import model.Local;
import org.hibernate.Query;
import org.hibernate.Session;

public class AbastecimientoDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<Abastecimiento> getListAbastecimientoSolicitudes() {
        List<Abastecimiento> listAbastecimiento = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Abastecimiento a inner join fetch a.estadoAbastecimiento ea inner join fetch a.localByLocalIdDestino lblid where ea.id in (1,3)");
            listAbastecimiento = (List<Abastecimiento>) query.list();
        } catch(Exception e) {
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return listAbastecimiento;
    }
    
    public List<Abastecimiento> getListAbastecimientoSolicitudestoLocal(Local local) {
        List<Abastecimiento> listAbastecimiento = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Abastecimiento a inner join fetch a.estadoAbastecimiento ea inner join fetch a.localByLocalIdDestino lblidd where lblidd.id= :localID  and ea.id in (1,2,3,5) order by a.id");
            query.setParameter("localID", local.getId());
            listAbastecimiento = (List<Abastecimiento>) query.list();
        } catch(Exception e) {
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return listAbastecimiento;
    }
}
