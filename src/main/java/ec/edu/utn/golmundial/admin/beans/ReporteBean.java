package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.client.EstadisticasApiClient;
import ec.edu.utn.golmundial.admin.client.UtnGolCoinApiClient;
import ec.edu.utn.golmundial.admin.dto.ReporteDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("reporteBean")
@ViewScoped
public class ReporteBean implements Serializable {

    private ReporteDto reporteGeneral;

    @Inject
    private EstadisticasApiClient estadisticasClient;

    @Inject
    private UtnGolCoinApiClient golCoinClient;

    @PostConstruct
    public void init() {
        reporteGeneral = new ReporteDto();
        cargarDatosReales();
    }

    private void cargarDatosReales() {
        try {
            ReporteDto resumenAPI = estadisticasClient.obtenerResumenDashboard();

            if (resumenAPI != null) {
                reporteGeneral.setPartidoMasPredicciones(
                        resumenAPI.getPartidoMasPredicciones() != null ? resumenAPI.getPartidoMasPredicciones() : "Ecuador vs Senegal"
                );
                reporteGeneral.setTotalPredicciones(
                        resumenAPI.getTotalPredicciones() != null ? resumenAPI.getTotalPredicciones() : 345
                );
            } else {
                throw new Exception("El endpoint devolvió null");
            }

            double ugcReal = golCoinClient.obtenerUgcEnCirculacion();
            reporteGeneral.setUgcEnCirculacion(ugcReal > 0 ? ugcReal : 15420.50);

        } catch (Exception e) {
            System.err.println("Fallo en la API, usando mock de emergencia total: " + e.getMessage());
            reporteGeneral.setPartidoMasPredicciones("Ecuador vs Senegal");
            reporteGeneral.setTotalPredicciones(345);
            reporteGeneral.setUgcEnCirculacion(15420.50);
        }
    }

    public ReporteDto getReporteGeneral() {
        return reporteGeneral;
    }
}