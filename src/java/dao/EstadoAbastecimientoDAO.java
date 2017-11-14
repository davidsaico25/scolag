package dao;

import java.io.Serializable;
import java.util.List;
import model.EstadoAbastecimiento;
import org.hibernate.Query;
import org.hibernate.Session;

public class EstadoAbastecimientoDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<EstadoAbastecimiento> getListEstadoAbastecimiento() {
        List<EstadoAbastecimiento> listEstadoAbastecimiento = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from EstadoAbastecimiento");
            listEstadoAbastecimiento = (List<EstadoAbastecimiento>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listEstadoAbastecimiento;
    }
}