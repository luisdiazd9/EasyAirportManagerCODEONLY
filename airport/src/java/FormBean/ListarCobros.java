/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormBean;

import Logica.ServicioCobro;
import Logica.ServicioUsuario;
import Modelo.Cobro;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
/**
 *
 * @author informatica
 */
public class ListarCobros implements Serializable {

    private List<Cobro> lista = new ArrayList<>();
    private Usuario usu = new Usuario();
    private Cobro cobroseleccionado;
    
    public void seleccionar(){
        try{
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("cobro",this.cobroseleccionado);
        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/cobrodescrito.xhtml");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ListarCobros() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (context.getExternalContext().getSessionMap().get("usuarioEntro") == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.xhtml");
            }else{
                this.usu = (Usuario) context.getExternalContext().getSessionMap().get("usuarioEntro");
                ServicioCobro s = new ServicioCobro();
                this.lista = s.listarCobros();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void nuevo() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().remove("cobro");
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/crearcobro.xhtml");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cobro> getLista() {
        return lista;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setLista(List<Cobro> lista) {
        this.lista = lista;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public Cobro getCobroseleccionado() {
        return cobroseleccionado;
    }

    public void setCobroseleccionado(Cobro cobroseleccionado) {
        this.cobroseleccionado = cobroseleccionado;
    }
    
    

}
