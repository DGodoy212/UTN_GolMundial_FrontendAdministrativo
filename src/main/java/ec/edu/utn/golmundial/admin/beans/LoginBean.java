package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.client.EstadisticasApiClient;
import ec.edu.utn.golmundial.admin.dto.LoginRequest;
import ec.edu.utn.golmundial.admin.dto.LoginResponse;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    // Inyectamos nuestro cliente estrella
    @Inject
    private EstadisticasApiClient apiClient;

    public String login() {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        LoginResponse response = apiClient.autenticar(request);

        if (response != null && response.getToken() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("token", response.getToken());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioAdminId", response.getUsuarioId());
            return "dashboard?faces-redirect=true";
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acceso Denegado", "Usuario o contraseña incorrectos o servidor apagado."));
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}