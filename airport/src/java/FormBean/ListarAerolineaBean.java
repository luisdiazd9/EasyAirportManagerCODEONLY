package FormBean;

import Logica.ServicioAerolinea;
import Logica.ServicioUsuario;
import Modelo.Aerolinea;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped

public class ListarAerolineaBean implements Serializable {

    private List<Aerolinea> lista = new ArrayList<>();
    private Usuario usu = new Usuario();
    private Aerolinea aerolineaseleccionada;

    public void seleccionar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("aerolinea", this.aerolineaseleccionada);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/aeroescrito.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListarAerolineaBean() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (context.getExternalContext().getSessionMap().get("usuarioEntro") == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/listarAerolinea.xhtml");

            } else {
                this.usu = (Usuario) context.getExternalContext().getSessionMap().get("usuarioEntro");
                ServicioAerolinea a = new ServicioAerolinea();
                this.lista = a.listarAerolineas();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void nuevo() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().remove("aerolinea");
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/crearaerolinea.xhtml");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Aerolinea> getLista() {
        return lista;
    }

    public Usuario getUsu() {
        return usu;
    }

    public Aerolinea getAerolineaseleccionada() {
        return aerolineaseleccionada;
    }

    public void setLista(List<Aerolinea> lista) {
        this.lista = lista;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public void setAerolineaseleccionada(Aerolinea aerolineaseleccionada) {
        this.aerolineaseleccionada = aerolineaseleccionada;
    }

   

}
