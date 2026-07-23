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
    private PartidoDto nuevoPartido = new PartidoDto();

    @Inject
    private EstadisticasApiClient apiClient;

    @PostConstruct
    public void init() {

        partidos = apiClient.obtenerPartidos();
    }

    public void prepararNuevo() {
        this.nuevoPartido = new PartidoDto();
        int siguienteNumero = 73;
        if (partidos != null && !partidos.isEmpty()) {
            int maxNumero = partidos.stream()
                    .mapToInt(p -> p.getNumeroPartidoFifa() != null ? p.getNumeroPartidoFifa() : 0)
                    .max()
                    .orElse(72);
            siguienteNumero = maxNumero + 1;
        }
        this.nuevoPartido.setNumeroPartidoFifa(siguienteNumero);

        this.nuevoPartido.setFase("GRUPOS");
        this.nuevoPartido.setGrupo("C");
        this.nuevoPartido.setSeleccionLocalId(6L);
        this.nuevoPartido.setSeleccionVisitanteId(7L);
        this.nuevoPartido.setSedeId(3L);
        this.nuevoPartido.setFechaHoraUtc("2026-06-11T19:00:00Z");
        this.nuevoPartido.setEstado("PROGRAMADO");
    }

    public void guardarEdicion() {
        if (partidoSeleccionado != null) {
            String fechaEc = partidoSeleccionado.getFechaHoraEcuador();
            if (fechaEc != null && fechaEc.contains(" ")) {
                fechaEc = fechaEc.replace(" ", "T"); // Reemplaza el espacio por la 'T'
                if (fechaEc.endsWith("-05")) {
                    fechaEc += ":00"; // Completa la zona horaria si PrimeFaces la cortó
                }
                partidoSeleccionado.setFechaHoraEcuador(fechaEc);
            }

            String fechaUtc = partidoSeleccionado.getFechaHoraUtc();
            if (fechaUtc != null && fechaUtc.contains(" ")) {
                partidoSeleccionado.setFechaHoraUtc(fechaUtc.replace(" ", "T"));
            }

            boolean exito = apiClient.actualizarPartido(partidoSeleccionado.getId(), partidoSeleccionado);

            if (exito) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Partido actualizado correctamente."));
                partidos = apiClient.obtenerPartidos();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el partido."));
            }
        }
    }

    public void guardarNuevoPartido() {
        if (nuevoPartido != null) {
            System.out.println("DEBUG Fase: " + nuevoPartido.getFase());
            System.out.println("DEBUG LocalId: " + nuevoPartido.getSeleccionLocalId());
            System.out.println("DEBUG VisitanteId: " + nuevoPartido.getSeleccionVisitanteId());
            System.out.println("DEBUG FechaUtc: " + nuevoPartido.getFechaHoraUtc());
            System.out.println("DEBUG SedeId: " + nuevoPartido.getSedeId());
            if (nuevoPartido.getFechaHoraUtc() != null && nuevoPartido.getFechaHoraUtc().trim().isEmpty()) {
                nuevoPartido.setFechaHoraUtc(null);
            }

            String fechaUtc = nuevoPartido.getFechaHoraUtc();
            if (fechaUtc != null && fechaUtc.contains(" ")) {
                nuevoPartido.setFechaHoraUtc(fechaUtc.replace(" ", "T"));
            }

            boolean exito = apiClient.registrarPartido(nuevoPartido);

            if (exito) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Partido registrado correctamente."));
                partidos = apiClient.obtenerPartidos();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar el partido en el backend."));
            }
        }
    }

    public List<PartidoDto> getPartidos() {
        return partidos;
    }
    public PartidoDto getPartidoSeleccionado() { return partidoSeleccionado; }
    public void setPartidoSeleccionado(PartidoDto partidoSeleccionado) { this.partidoSeleccionado = partidoSeleccionado; }
    public PartidoDto getNuevoPartido() { return nuevoPartido; }
    public void setNuevoPartido(PartidoDto nuevoPartido) { this.nuevoPartido = nuevoPartido; }
}