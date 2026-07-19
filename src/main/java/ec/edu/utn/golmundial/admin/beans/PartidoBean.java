package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.dto.PartidoDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("partidoBean")
@ViewScoped
public class PartidoBean implements Serializable {

    private List<PartidoDto> partidos;

    @PostConstruct
    public void init() {
        partidos = new ArrayList<>();

        PartidoDto mock1 = new PartidoDto();
        mock1.setId(1L);
        mock1.setFechaHoraUtc("2026-06-11T19:00:00Z");
        mock1.setSeleccionLocalNombre("México");
        mock1.setSeleccionVisitanteNombre("Sudáfrica");
        mock1.setSedeNombre("Estadio Azteca");
        mock1.setEstado("PROGRAMADO");

        PartidoDto mock2 = new PartidoDto();
        mock2.setId(2L);
        mock2.setFechaHoraUtc("2026-06-12T15:00:00Z");
        mock2.setSeleccionLocalNombre("Ecuador");
        mock2.setSeleccionVisitanteNombre("Qatar");
        mock2.setSedeNombre("Estadio Monumental");
        mock2.setEstado("EN_JUEGO");

        partidos.add(mock1);
        partidos.add(mock2);
    }

    public List<PartidoDto> getPartidos() {
        return partidos;
    }
}