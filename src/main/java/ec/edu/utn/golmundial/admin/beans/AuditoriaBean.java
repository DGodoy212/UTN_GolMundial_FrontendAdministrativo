package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.dto.AuditoriaDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("auditoriaBean")
@ViewScoped
public class AuditoriaBean implements Serializable {

    private List<AuditoriaDto> registros;

    @PostConstruct
    public void init() {
        registros = new ArrayList<>();

        // Datos mock del historial de acciones
        AuditoriaDto a1 = new AuditoriaDto();
        a1.setId(101L);
        a1.setUsuarioId(1L);
        a1.setAccion("REGISTRO_RESULTADO");
        a1.setEntidad("Partido");
        a1.setEntidadId(12L);
        a1.setFechaHoraUtc("2026-06-15T22:00:00Z");
        a1.setDetalle("Se registró el marcador 2-0 a favor del local");

        AuditoriaDto a2 = new AuditoriaDto();
        a2.setId(102L);
        a2.setUsuarioId(2L);
        a2.setAccion("EDICION_PARTIDO");
        a2.setEntidad("Partido");
        a2.setEntidadId(5L);
        a2.setFechaHoraUtc("2026-06-16T10:30:00Z");
        a2.setDetalle("Cambio de sede a Estadio Monumental");

        registros.add(a1);
        registros.add(a2);
    }

    public List<AuditoriaDto> getRegistros() {
        return registros;
    }
}