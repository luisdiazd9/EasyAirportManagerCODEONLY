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
public class ServicioCobro implements Serializable{
    private Session sesion;
    private Transaction tr;

    public ServicioCobro() {
         sesion = NewHibernateUtil.getSessionFactory().openSession();
    }
    
    public Cobro guardarcobro(Cobro c) {
        boolean ok = false;
        try {
           
            sesion.getTransaction().begin();
            sesion.save(c);
            sesion.getTransaction().commit();
            sesion.evict(c);
            
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
            sesion.getTransaction().rollback();
        }
        return c;
    }
    
    public Cobro consultarcobro(Integer id) {
        Cobro c = null;
        try {
            
            
            c = (Cobro) this.sesion.get(Cobro.class, id);
            sesion.evict(c);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public Cobro actualizarcobro(Cobro o){
        boolean ok= false;
        try {
            
            sesion.getTransaction().begin();
            sesion.update(o);
            sesion.getTransaction().commit();
            sesion.evict(o);
            
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
            sesion.getTransaction().rollback();
        }
        return o;
    }
    
    public Cobro eliminacobro(int id){
        boolean ok = false;
        Cobro cobro=null;
        try {    
            sesion.getTransaction().begin();
            cobro=(Cobro) sesion.get(Cobro.class, id);
            sesion.delete(cobro); 
            sesion.getTransaction().commit();
            sesion.evict(cobro);
            ok=true;
        } catch (Exception e) {
            System.out.println(e);
            ok=false;
        }
        return cobro;
    }
    
     public List<Cobro> listarCobros() {
        List<Cobro> lista = null;
        try {
            
            lista = this.sesion.createQuery("From Cobro").list();
            for (Cobro cobro :lista){
                this.sesion.evict(cobro);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
