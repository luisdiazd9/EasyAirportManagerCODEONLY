
package FormBean;

import Logica.ServicioUsuario;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ListarUsuario implements Serializable{
    
    private List<Usuario> lista = new ArrayList<>();
    private Usuario usu = new Usuario();
    private Usuario usuarioseleccionado;
    
    public ListarUsuario() 
    {  
            //ServicioUsuario s=new ServicioUsuario();
           try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (context.getExternalContext().getSessionMap().get("usuarioEntro") == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/listarusuario.xhtml");

            } else {
                this.usu = (Usuario) context.getExternalContext().getSessionMap().get("usuarioEntro");
                ServicioUsuario a = new ServicioUsuario();
                this.lista = a.listarUsuarios();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }                   
        
    }
    
    public void seleccionar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("usuario", this.usuarioseleccionado);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/usuariodescrito.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public void nuevo() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().remove("usuario");
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/crearusuario.xhtml");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Usuario> getLista(){
        return lista;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public Usuario getUsuarioseleccionado() {
        return usuarioseleccionado;
    }

    public void setUsuarioseleccionado(Usuario usuarioseleccionado) {
        this.usuarioseleccionado = usuarioseleccionado;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }
    
    
}
