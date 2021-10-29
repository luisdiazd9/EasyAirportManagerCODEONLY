/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormBean;

import Logica.ServicioAerolinea;
import Logica.ServicioCobro;
import Modelo.Aerolinea;
import Modelo.Cobro;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

@ManagedBean
@ViewScoped
/**
 *
 * @author admin
 */
public class CobroBean implements Serializable {

    private Cobro descrito = new Cobro();
    private Usuario usu = new Usuario();
    private List<Aerolinea> listadeaerolinea = new ArrayList<>();

    public CobroBean() {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            if (ctx.getExternalContext().getSessionMap().get("cobro") == null) {
                descrito = new Cobro();
                this.descrito.setAerolinea(new Aerolinea(0));
                 this.llenarListas();
            } else {
                this.descrito = (Cobro) ctx.getExternalContext().getSessionMap().get("cobro");

            }
            this.llenarListas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cobro getDescrito() {
        return descrito;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setDescrito(Cobro descrito) {
        this.descrito = descrito;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public void llenarListas() {
        ServicioAerolinea sa = new ServicioAerolinea();
        this.listadeaerolinea = sa.listarAerolineas();


    }

    public void actualizar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (this.descrito.getAerolinea().getId() != 0) {
                ServicioCobro sc = new ServicioCobro();
                if (this.descrito.getId() == null) {
                    this.descrito = sc.actualizarcobro(this.descrito);
                } else {
                    this.descrito = sc.actualizarcobro(descrito);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", "Se actualizó"));
                    context.getExternalContext().getSessionMap().put("cobro", this.descrito);
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Complete los datos"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public void guardar() {
        try {
            this.llenarListas();
            FacesContext context = FacesContext.getCurrentInstance();
            if (this.descrito.getAerolinea().getId() != 0) {
                ServicioCobro sc = new ServicioCobro();
                if (this.descrito.getId() == null) {
                    this.descrito = sc.guardarcobro(descrito);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Se creó"));
                    context.getExternalContext().getSessionMap().put("cobro", this.descrito);
                } else {
                    this.descrito = sc.actualizarcobro(descrito);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", "Se actualizó"));
                    context.getExternalContext().getSessionMap().put("descrito", this.descrito);
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Complete los datos"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ServicioCobro sc = new ServicioCobro();
            sc.eliminacobro(this.descrito.getId());
            context.getExternalContext().getSessionMap().remove("cobro");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/listarcobros.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public List<Aerolinea> getListadeaerolinea() {
        return listadeaerolinea;
    }

    public void setListadeaerolinea(List<Aerolinea> listadeaerolinea) {
        this.listadeaerolinea = listadeaerolinea;
    }

}

/*
listo es que el atrinbbuto es de tipo Date.
y el imput que tenian es String 
entonces toca colocar es el componente calendar que el valor del calendar es un atributo de tipo Date
me desconecto ya estan entrando a actualizar
 */
