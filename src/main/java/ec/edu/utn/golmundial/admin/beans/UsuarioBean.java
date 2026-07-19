package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.dto.UsuarioDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    private List<UsuarioDto> usuarios;

    @PostConstruct
    public void init() {
        usuarios = new ArrayList<>();

        // Datos mock para probar la vista
        UsuarioDto u1 = new UsuarioDto();
        u1.setId(1L);
        u1.setNombre("Daniel Godoy");
        u1.setEmail("dgodoy@utn.edu.ec");
        u1.setRol("ADMINISTRADOR");
        u1.setActivo(true);

        UsuarioDto u2 = new UsuarioDto();
        u2.setId(2L);
        u2.setNombre("Estudiante Prueba");
        u2.setEmail("estudiante@utn.edu.ec");
        u2.setRol("USUARIO");
        u2.setActivo(true);

        usuarios.add(u1);
        usuarios.add(u2);
    }

    public List<UsuarioDto> getUsuarios() {
        return usuarios;
    }
}