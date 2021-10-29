/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Aerolinea;
import Modelo.Cobro;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author admin
 */
public class ServicioAerolinea implements Serializable {

    private Session sesion;
    private Transaction tr;

    public ServicioAerolinea() {
        sesion = NewHibernateUtil.getSessionFactory().openSession();
    }

    public int incrementarid(Integer id) {
        Aerolinea aerolinea = new Aerolinea();
        sesion.getTransaction().begin();
        aerolinea = (Aerolinea) sesion.get(Aerolinea.class, id);
        sesion.getTransaction().commit();
        int nuevoid = 0;
        nuevoid = aerolinea.getId() + 1;
        return nuevoid;
    }

    public Aerolinea guardaraerolinea(Aerolinea u) {
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

    public Aerolinea consultarPorID(Integer id) {
        Aerolinea a = null;
        try {

            a = (Aerolinea) this.sesion.get(Aerolinea.class, id);
            sesion.evict(a);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public Aerolinea actualizaraerolinea(Aerolinea u) {
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

    public Aerolinea eliminaraerolinea(int id) {
        boolean ok = false;
        Aerolinea aerolinea = null;
        try {
            sesion.getTransaction().begin();
            aerolinea = (Aerolinea) sesion.get(Aerolinea.class, id);
            sesion.delete(aerolinea);
            sesion.getTransaction().commit();
            sesion.evict(aerolinea);
            ok = true;
        } catch (Exception e) {
            ok = false;
        }
        return aerolinea;
    }

    public List<Aerolinea> listarAerolineas() {
        List<Aerolinea> lista = null;
        try {
            lista = this.sesion.createQuery("From Aerolinea").list();
            for (Aerolinea aerolinea : lista) {
                this.sesion.evict(aerolinea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
