package dao;
import java.io.Serializable;
import java.util.List;
import model.*;
import org.hibernate.Query;
import org.hibernate.Session;

public class OrdenCompraHasPresentacionInsumoDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<OrdenCompraHasPresentacionInsumo> getListOrdenCompraHasPresentacionInsumo(OrdenCompra oc) {
        List<OrdenCompraHasPresentacionInsumo> listOrdenCompraHasPresentacionInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from OrdenCompraHasPresentacionInsumo ochpi inner join fetch ochpi.ordenCompra oc inner join fetch ochpi.presentacionInsumo pi inner join fetch pi.insumo i inner join fetch i.unidadMedida um where oc.id = :ocID");
            query.setParameter("ocID", oc.getId());
            listOrdenCompraHasPresentacionInsumo = (List<OrdenCompraHasPresentacionInsumo>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listOrdenCompraHasPresentacionInsumo;
    }
}
