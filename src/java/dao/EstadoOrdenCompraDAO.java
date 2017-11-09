package dao;

import java.io.Serializable;
import java.util.List;
import model.EstadoOrdenCompra;
import org.hibernate.Query;
import org.hibernate.Session;

public class EstadoOrdenCompraDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<EstadoOrdenCompra> getListEstadoOrdenCompra() {
        List<EstadoOrdenCompra> listEstadoOrdenCompra = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from EstadoOrdenCompra");
            listEstadoOrdenCompra = (List<EstadoOrdenCompra>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listEstadoOrdenCompra;
    }
}
