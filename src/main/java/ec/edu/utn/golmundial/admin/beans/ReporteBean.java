package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.dto.ReporteDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("reporteBean")
@ViewScoped
public class ReporteBean implements Serializable {

    private ReporteDto reporteGeneral;

    @PostConstruct
    public void init() {
        // Datos mock para el dashboard de reportes
        reporteGeneral = new ReporteDto();
        reporteGeneral.setPartidoMasPredicciones("Ecuador vs Senegal");
        reporteGeneral.setTotalPredicciones(345);
        reporteGeneral.setUgcEnCirculacion(15420.50);
    }

    public ReporteDto getReporteGeneral() {
        return reporteGeneral;
    }
}