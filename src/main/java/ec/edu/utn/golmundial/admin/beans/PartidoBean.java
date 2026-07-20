package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.client.EstadisticasApiClient;
import ec.edu.utn.golmundial.admin.dto.PartidoDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named("partidoBean")
@ViewScoped
public class PartidoBean implements Serializable {
    private List<PartidoDto> partidos;
    private PartidoDto partidoSeleccionado;

    @Inject
    private EstadisticasApiClient apiClient;

    @PostConstruct
    public void init() {

        partidos = apiClient.obtenerPartidos();
    }

    public void guardarEdicion() {
        if (partidoSeleccionado != null) {
            boolean exito = apiClient.actualizarPartido(partidoSeleccionado.getId(), partidoSeleccionado);

            if (exito) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Partido actualizado correctamente."));
                // Recargamos la lista para ver los cambios
                partidos = apiClient.obtenerPartidos();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el partido."));
            }
        }
    }

    public List<PartidoDto> getPartidos() {
        return partidos;
    }
    public PartidoDto getPartidoSeleccionado() { return partidoSeleccionado; }
    public void setPartidoSeleccionado(PartidoDto partidoSeleccionado) { this.partidoSeleccionado = partidoSeleccionado; }
}