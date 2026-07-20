package ec.edu.utn.golmundial.admin.dto;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    // Clase extra, empaca las credenciales del usuario
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}