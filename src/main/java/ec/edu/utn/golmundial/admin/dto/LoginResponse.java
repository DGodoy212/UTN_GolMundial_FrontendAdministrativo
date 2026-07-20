package ec.edu.utn.golmundial.admin.dto;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    // Clase extra, atrapa el JSON del backend Estadisticas
    private String token;
    private Long usuarioId;
    private String rol;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}