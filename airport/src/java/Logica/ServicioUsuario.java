/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.Transaction;

/**
 *
 * @author admin
 */
public class ServicioUsuario implements Serializable {

    private Session sesion;
    private Transaction tr;

    public ServicioUsuario() {
        sesion = NewHibernateUtil.getSessionFactory().openSession();
    }

    public Usuario generarusuario(Usuario u) {
        boolean ok = false;
        try {
            sesion.getTransaction().begin();
            sesion.save(u);
            sesion.getTransaction().commit();
            sesion.evict(u);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
            sesion.getTransaction().rollback();
        }
        return u;
    }

    public Usuario eliminarusuario(int id) {
        boolean ok = false;
        Usuario usuario = null;
        try {
            sesion.getTransaction().begin();
            usuario = (Usuario) sesion.get(Usuario.class, id);
            sesion.delete(usuario);
            sesion.getTransaction().commit();
            sesion.evict(usuario);
            ok = true;
        } catch (Exception e) {
            ok = false;
        }
        return usuario;
    }

    public Usuario editarusuario(Usuario u) {
        boolean ok = false;
        try {
            sesion.getTransaction().begin();
            sesion.update(u);
            sesion.getTransaction().commit();
            sesion.evict(u);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
            sesion.getTransaction().rollback();
        }
        return u;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = null;
        try {
            lista = this.sesion.createQuery("From Usuario").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Usuario validarUsuario(String usUsuario, String claveUsuario) {
        Usuario usuario = null;

        try {

            sesion = NewHibernateUtil.getSessionFactory().openSession();
            tr = sesion.beginTransaction();
            usuario = (Usuario) sesion.createQuery("from Usuario where usuario =:usu and clave=:claveUsuario")
                    .setParameter("usu", usUsuario)
                    .setParameter("claveUsuario", claveUsuario)
                    .uniqueResult();

            sesion.getTransaction().commit();
            sesion.close();

        } catch (Exception e) {
            e.printStackTrace();
            usuario = null;
        }

        return usuario;

    }

}
