package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.client.EstadisticasApiClient;
import ec.edu.utn.golmundial.admin.dto.UsuarioDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    private List<UsuarioDto> usuarios;
    private UsuarioDto usuarioSeleccionado = new UsuarioDto();

    @Inject
    private EstadisticasApiClient apiClient;

    @PostConstruct
    public void init() {
        usuarios = new ArrayList<>();
        cargarDatosReales();
    }

    private void cargarDatosReales() {
        try {
            List<UsuarioDto> usuariosApi = apiClient.obtenerUsuarios();

            if (usuariosApi != null && !usuariosApi.isEmpty()) {
                this.usuarios = usuariosApi;
            } else {
                throw new Exception("La API devolvió una lista vacía o nula");
            }
        } catch (Exception e) {
            System.err.println("Fallo al cargar usuarios reales, usando mock: " + e.getMessage());
            cargarMockDeEmergencia();

            jakarta.faces.context.FacesContext.getCurrentInstance().addMessage(null,
                    new jakarta.faces.application.FacesMessage(
                            jakarta.faces.application.FacesMessage.SEVERITY_WARN,
                            "Modo Offline / Contingencia",
                            "No se pudo conectar al endpoint de usuarios. Mostrando datos falsos."
                    ));
        }
    }

    private UsuarioDto nuevoUsuario = new UsuarioDto();
    private String passwordTemp; // Campo temporal para la contraseña del registro

    public void prepararNuevo() {
        this.nuevoUsuario = new UsuarioDto();
        this.passwordTemp = "";
    }

    public void guardarNuevoUsuario() {
        try {
            if (nuevoUsuario.getRol() == null || nuevoUsuario.getRol().isEmpty()) {
                nuevoUsuario.setRol("USUARIO");
            }
            nuevoUsuario.setActivo(true);
            nuevoUsuario.setPassword(passwordTemp);

            boolean exito = apiClient.registrarUsuario(nuevoUsuario);

            if (exito) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario registrado y guardado correctamente."));
                cargarDatosReales();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El backend rechazó el registro. Revisa la consola de IntelliJ."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Excepción al registrar: " + e.getMessage()));
        }
    }

    public void guardarEdicion() {
        if (usuarioSeleccionado != null && usuarioSeleccionado.getId() != null) {
            boolean exito = apiClient.actualizarUsuario(usuarioSeleccionado.getId(), usuarioSeleccionado);

            if (exito) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario actualizado correctamente."));
                cargarDatosReales();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar. Revisa si el backend soporta este endpoint."));
            }
        }
    }

    public void eliminar(UsuarioDto usuario) {
        if (usuario != null && usuario.getId() != null) {
            boolean exito = apiClient.eliminarUsuario(usuario.getId());

            if (exito) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario eliminado correctamente."));
                cargarDatosReales();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el usuario del servidor."));
            }
        }
    }

    private void cargarMockDeEmergencia() {
        UsuarioDto u1 = new UsuarioDto();
        u1.setId(1L);
        u1.setNombre("Anthropic Claude");
        u1.setUsername("Optimus Claude");
        u1.setEmail("Cl@ude.com");
        u1.setRol("ADMINISTRADOR");
        u1.setActivo(true);

        UsuarioDto u2 = new UsuarioDto();
        u2.setId(2L);
        u2.setNombre("Chat GPT");
        u2.setUsername("GPTron");
        u2.setEmail("open@ai.com");
        u2.setRol("USUARIO");
        u2.setActivo(true);

        usuarios.add(u1);
        usuarios.add(u2);
    }

    // Getter y Setter necesarios para el formulario modal
    public UsuarioDto getNuevoUsuario() { return nuevoUsuario; }
    public void setNuevoUsuario(UsuarioDto nuevoUsuario) { this.nuevoUsuario = nuevoUsuario; }
    public String getPasswordTemp() { return passwordTemp; }
    public void setPasswordTemp(String passwordTemp) { this.passwordTemp = passwordTemp; }
    public List<UsuarioDto> getUsuarios() {
        return usuarios;
    }
    public UsuarioDto getUsuarioSeleccionado() { return usuarioSeleccionado; }
    public void setUsuarioSeleccionado(UsuarioDto usuarioSeleccionado) { this.usuarioSeleccionado = usuarioSeleccionado; }
}