
package FormBean;

import Logica.ServicioAerolinea;
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
@ManagedBean
@ViewScoped


public class AerolineaBean {
    private Aerolinea descrito=new Aerolinea();  
    private Usuario usu=new Usuario();
    public AerolineaBean(){
        try{
        FacesContext ctx= FacesContext.getCurrentInstance();
        if(ctx.getExternalContext().getSessionMap().get("aerolinea") == null){
            descrito=new Aerolinea();
            //this.llenarlistas();
        }else{
            this.descrito=(Aerolinea)ctx.getExternalContext().getSessionMap().get("aerolinea");
        }
        //this.llenarlistas();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void guardar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (this.descrito.getNombre()!= null) {
                ServicioAerolinea sa = new ServicioAerolinea();
                if (this.descrito.getId() == null) {
                    this.descrito = sa.guardaraerolinea(descrito);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Se creó"));
                    context.getExternalContext().getSessionMap().put("aerolinea", this.descrito);
                } else {
                    this.descrito = sa.actualizaraerolinea(descrito);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", "Se actualizó"));
                    context.getExternalContext().getSessionMap().put("descrito", this.descrito);
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Complete los datos"));
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    public void actualizar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (this.descrito.getId() != null) {
                ServicioAerolinea sa = new ServicioAerolinea();
                if (this.descrito.getId() == null) {
                    
                } else {
                    this.descrito = sa.actualizaraerolinea(descrito);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", "Se actualizó"));
                    context.getExternalContext().getSessionMap().put("aerolinea", this.descrito);
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
            ServicioAerolinea sa = new ServicioAerolinea();
            sa.eliminaraerolinea(this.descrito.getId());
            context.getExternalContext().getSessionMap().remove("aerolinea");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/listarAerolinea.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Aerolinea getDescrito() {
        return descrito;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setDescrito(Aerolinea descrito) {
        this.descrito = descrito;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }
    
}
