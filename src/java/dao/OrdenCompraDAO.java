package dao;

import java.io.Serializable;
import java.util.List;
import model.OrdenCompra;
import org.hibernate.Query;
import org.hibernate.Session;

public class OrdenCompraDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<OrdenCompra> getListOrdenCompra() {
        List<OrdenCompra> listOrdenCompra = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from OrdenCompra oc inner join fetch oc.estadoOrdenCompra eoc where eoc.id=1");
            listOrdenCompra = (List<OrdenCompra>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listOrdenCompra;
    }
}
