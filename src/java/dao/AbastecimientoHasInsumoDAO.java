package dao;

import java.io.Serializable;
import java.util.List;
import model.Abastecimiento;
import model.AbastecimientoHasInsumo;
import org.hibernate.Query;
import org.hibernate.Session;

public class AbastecimientoHasInsumoDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<AbastecimientoHasInsumo> getListAbastecimientoHasInsumo(Abastecimiento abastecimiento) {
        List<AbastecimientoHasInsumo> listAbastecimientoHasInsumo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from AbastecimientoHasInsumo ahi inner join fetch ahi.abastecimiento a inner join fetch ahi.insumo i inner join fetch i.unidadMedida um where a.id= :abastecimientoID");
            query.setParameter("abastecimientoID", abastecimiento.getId());
            listAbastecimientoHasInsumo = (List<AbastecimientoHasInsumo>) query.list();
        } catch(Exception e) {
        }finally {
            if (session != null) {
                session.close();
            }
        }        
        return listAbastecimientoHasInsumo;
    }
}
