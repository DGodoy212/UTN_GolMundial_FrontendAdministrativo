package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.client.EstadisticasApiClient;
import ec.edu.utn.golmundial.admin.dto.AuditoriaDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("auditoriaBean")
@ViewScoped
public class AuditoriaBean implements Serializable {

    private List<AuditoriaDto> registros;

    @Inject
    private EstadisticasApiClient apiClient;

    @PostConstruct
    public void init() {
        registros = apiClient.obtenerAuditoria();
    }

    public List<AuditoriaDto> getRegistros() {
        return registros;
    }
}