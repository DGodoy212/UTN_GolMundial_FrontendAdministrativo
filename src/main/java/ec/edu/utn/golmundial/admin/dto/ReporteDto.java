package ec.edu.utn.golmundial.admin.dto;

public class ReporteDto {
    private String partidoMasPredicciones;
    private Integer totalPredicciones;
    private Double ugcEnCirculacion;

    public String getPartidoMasPredicciones() {
        return partidoMasPredicciones;
    }

    public void setPartidoMasPredicciones(String partidoMasPredicciones) {
        this.partidoMasPredicciones = partidoMasPredicciones;
    }

    public Integer getTotalPredicciones() {
        return totalPredicciones;
    }

    public void setTotalPredicciones(Integer totalPredicciones) {
        this.totalPredicciones = totalPredicciones;
    }

    public Double getUgcEnCirculacion() {
        return ugcEnCirculacion;
    }

    public void setUgcEnCirculacion(Double ugcEnCirculacion) {
        this.ugcEnCirculacion = ugcEnCirculacion;
    }
}
