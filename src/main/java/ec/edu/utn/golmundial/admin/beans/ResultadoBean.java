package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.dto.PartidoDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("resultadoBean")
@ViewScoped
public class ResultadoBean implements Serializable {

    private List<PartidoDto> partidosPendientes;

    @PostConstruct
    public void init() {
        partidosPendientes = new ArrayList<>();

        // Datos mock de partidos esperando resultado
        PartidoDto p1 = new PartidoDto();
        p1.setId(10L);
        p1.setSeleccionLocalNombre("Argentina");
        p1.setSeleccionVisitanteNombre("Colombia");
        p1.setEstado("EN_JUEGO"); // Listo para finalizar
        p1.setGolesLocal(0);
        p1.setGolesVisitante(0);

        partidosPendientes.add(p1);
    }

    public List<PartidoDto> getPartidosPendientes() {
        return partidosPendientes;
    }
}