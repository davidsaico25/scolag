package dao;
import java.io.Serializable;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;
import model.RequerimientoHasPresentacionInsumo;

public class RequerimientoHasPresentacionInsumoDAO extends ADAO_crud<Object> implements Serializable {
    
    public List<RequerimientoHasPresentacionInsumo> getLisrRequerimientoHasPresentacionInsumos(){
        List<RequerimientoHasPresentacionInsumo> ListRequerimientoHasInsumo=null;
        Session session = null;
        try{
             session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from requerimiento_has_presentacion_insumo rhi inner join fetch rhi.requerimiento r inner join fetch r.monto_total");
            ListRequerimientoHasInsumo=(List<RequerimientoHasPresentacionInsumo>)query.list();
        }
        catch(Exception e){
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return ListRequerimientoHasInsumo;
        }
    }
