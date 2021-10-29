package FormBean;

import Logica.ServicioUsuario;
import Modelo.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private String user = "";
    private String clave = "";
    private Usuario us=new Usuario();
    private Usuario descrito=new Usuario();
    private ServicioUsuario su=new ServicioUsuario();

    public UsuarioBean() {
         try{
        FacesContext ctx= FacesContext.getCurrentInstance();
        if(ctx.getExternalContext().getSessionMap().get("usuario") == null){
            descrito=new Usuario();
            //this.llenarlistas();
        }else{
            this.descrito=(Usuario)ctx.getExternalContext().getSessionMap().get("usuario");
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
                ServicioUsuario su = new ServicioUsuario();
                if (this.descrito.getId() == null) {
                    this.descrito = su.generarusuario(descrito);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Se creó"));
                    context.getExternalContext().getSessionMap().put("usuario", this.descrito);
                } else {
                    this.descrito = su.editarusuario(descrito);
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
                ServicioUsuario su = new ServicioUsuario();
                if (this.descrito.getId() == null) {
                    
                } else {
                    this.descrito = su.editarusuario(descrito);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", "Se actualizó"));
                    context.getExternalContext().getSessionMap().put("usuario", this.descrito);
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
            ServicioUsuario su = new ServicioUsuario();
            su.eliminarusuario(this.descrito.getId());
            context.getExternalContext().getSessionMap().remove("usuario");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/listarusuario.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public Usuario getDescrito() {
        return descrito;
    }

    public void setDescrito(Usuario descrito) {
        this.descrito = descrito;
    }

    public ServicioUsuario getSu() {
        return su;
    }

    public void setSu(ServicioUsuario su) {
        this.su = su;
    }
    
    public void ingresar() {

        try {
            
            FacesContext context = FacesContext.getCurrentInstance();
            if(su.validarUsuario(user, clave)!=null){
                context.getExternalContext().getSessionMap().put("nombre", this.user);
                context.getExternalContext().getSessionMap().put("clave", this.clave);
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/principal.xhtml");
                us=su.validarUsuario(user, clave);
                context.getExternalContext().getSessionMap().put("usuarioEntro", us);
               
            }
            else{
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe:",user));
            }
            

        } catch (Exception e) {
        }

    }
    
    
    
    
    

}
