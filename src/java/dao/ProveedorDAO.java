package dao;

import java.io.Serializable;
import java.util.List;
import model.*;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProveedorDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<Proveedor> getListProveedor() {
        List<Proveedor> listProveedor = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Proveedor");
            listProveedor = (List<Proveedor>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listProveedor;
    }
}
