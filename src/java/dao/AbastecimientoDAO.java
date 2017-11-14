package dao;

import java.io.Serializable;
import java.util.List;
import model.Abastecimiento;
import model.AbastecimientoHasInsumo;
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
}
