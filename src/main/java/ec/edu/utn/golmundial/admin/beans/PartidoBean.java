package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.client.EstadisticasApiClient;
import ec.edu.utn.golmundial.admin.dto.PartidoDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named("partidoBean")
@ViewScoped
public class PartidoBean implements Serializable {
    private List<PartidoDto> partidos;

    @Inject
    private EstadisticasApiClient apiClient;

    @PostConstruct
    public void init() {

        partidos = apiClient.obtenerPartidos();
    }

    public List<PartidoDto> getPartidos() {
        return partidos;
    }
}