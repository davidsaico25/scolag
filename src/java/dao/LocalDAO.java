package dao;

import java.io.Serializable;
import java.util.List;
import model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LocalDAO extends ADAO_crud<Object> implements Serializable {

    public List<Local> getListLocal() {
        List<Local> listLocal = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Local");
            listLocal = (List<Local>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listLocal;
    }

    public List<Local> getListLocalAbastecimiento(Usuario usuarioJefeAlmacen) {
        List<Local> listLocal = null;
        Session session = null;
        Local localUsuarioJefeAlmacen = getLocalUsuario(usuarioJefeAlmacen);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Local");
            listLocal = (List<Local>) query.list();
            for (Local item : listLocal) {
                if (item.getId() == localUsuarioJefeAlmacen.getId()) {
                    listLocal.remove(item);
                    break;
                }
            }
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listLocal;
    }

    public Local getLocalUsuario(Usuario usuario) {
        Session session = null;
        Local local = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from LocalHasUsuario lhu inner join fetch lhu.usuario u inner join fetch lhu.local l where u.username = :username");
            query.setParameter("username", usuario.getUsername());
            LocalHasUsuario localHasUsuario = (LocalHasUsuario) query.uniqueResult();
            local = localHasUsuario.getLocal();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return local;
    }
}
