package FormBean;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class PrincipalBean implements Serializable {

    public PrincipalBean() {

    }

    public void verificarSesion() throws IOException
    {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre") != null) {
            String nombre = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", nombre));
            RequestContext.getCurrentInstance().update("mensaje");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }

}
