package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import model.Perfil;
import model.PerfilHasSubmenu;
import model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDAO extends ADAO_crud<Object> implements Serializable {
    
    public Usuario login(Usuario usuarioParam) {
        Usuario usuario = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Usuario u inner join fetch u.persona inner join fetch u.perfil p where u.username = :username");
            query.setParameter("username", usuarioParam.getUsername());
            usuario = (Usuario) query.uniqueResult();
            return usuario;
            /*
            System.out.println("RESULT:");
            System.out.println(usuario.getUsername() + " - " + usuario.getPassword());
            //usuario.setPerfil(usuario.getPerfil());
            System.out.println(usuario.getPerfil().getNombre());
            Set setPerfilHasSubmenus = usuario.getPerfil().getPerfilHasSubmenus();
            for (Object perfilHasSubmenus : setPerfilHasSubmenus) {
                PerfilHasSubmenu phsm = (PerfilHasSubmenu) perfilHasSubmenus;
                System.out.println(phsm.getSubmenu().getMenu().getNombre() + " - " + phsm.getSubmenu().getNombre());
            }
            if(usuario.getPassword().equals(usuarioParam.getPassword())) {
                return usuario;
            }*/
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
