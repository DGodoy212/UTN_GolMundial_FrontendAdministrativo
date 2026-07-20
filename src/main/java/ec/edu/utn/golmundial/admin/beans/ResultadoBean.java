package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.client.EstadisticasApiClient;
import ec.edu.utn.golmundial.admin.dto.PartidoDto;
import ec.edu.utn.golmundial.admin.dto.ResultadoPartidoRequest;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("resultadoBean")
@ViewScoped
public class ResultadoBean implements Serializable {

    private List<PartidoDto> partidosPendientes;

    @Inject
    private EstadisticasApiClient apiClient;

    @PostConstruct
    public void init() {
        cargarPartidosPendientes();
    }

    public void cargarPartidosPendientes() {
        List<PartidoDto> todosLosPartidos = apiClient.obtenerPartidos();

        if (todosLosPartidos != null) {
            partidosPendientes = todosLosPartidos.stream()
                    .filter(p -> !"FINALIZADO".equals(p.getEstado()))
                    .collect(Collectors.toList());
        }
    }

    public void guardarResultado(PartidoDto partido) {
        ResultadoPartidoRequest request = new ResultadoPartidoRequest();
        request.setGolesLocal(partido.getGolesLocal());
        request.setGolesVisitante(partido.getGolesVisitante());

        // Por ahora quemamos el ID del admin (1L) para pruebas
        request.setUsuarioAdminId(1L);
        boolean exito = apiClient.registrarResultado(partido.getId(), request);
        if (exito) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Resultado registrado y monedas liquidadas."));
            cargarPartidosPendientes();
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar el marcador en el backend."));
        }
    }

    public List<PartidoDto> getPartidosPendientes() {
        return partidosPendientes;
    }
}