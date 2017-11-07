package dao;

import java.io.Serializable;
import java.util.List;
import model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDAO extends ADAO_crud<Object> implements Serializable {
    
    public Usuario login(Usuario usuarioParam) {
        Usuario usuario = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Usuario u inner join fetch u.persona inner join fetch u.perfil p where u.username = :username and u.password = :password");
            query.setParameter("username", usuarioParam.getUsername());
            query.setParameter("password", usuarioParam.getPassword());
            usuario = (Usuario) query.uniqueResult();
            return usuario;
        } catch(Exception e) {
        }finally {
            if (session != null) {
                session.close();
            }
        }
        
        return null;
    }
    
    public List<Usuario> getListUsuario() {
        List<Usuario> listUsuario = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Usuario u inner join fetch u.persona p");
            listUsuario = (List<Usuario>) query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listUsuario;
    }
}
