package ec.edu.utn.golmundial.admin.dto;

public class ReporteDto {
    private String partidoMasPredicciones;
    private Integer totalPredicciones;
    private Double ugcEnCirculacion;

    private Integer totalPartidos;
    private Integer partidosFinalizados;
    private Boolean success;

    // Getters y Setters corregidos
    public String getPartidoMasPredicciones() {
        return partidoMasPredicciones != null ? partidoMasPredicciones : "Sin registros";
    }
    public void setPartidoMasPredicciones(String partidoMasPredicciones) { this.partidoMasPredicciones = partidoMasPredicciones; }

    public Integer getTotalPredicciones() {
        return totalPredicciones != null ? totalPredicciones : 0;
    }
    public void setTotalPredicciones(Integer totalPredicciones) { this.totalPredicciones = totalPredicciones; }

    public Double getUgcEnCirculacion() { return ugcEnCirculacion != null ? ugcEnCirculacion : 0.0; }
    public void setUgcEnCirculacion(Double ugcEnCirculacion) { this.ugcEnCirculacion = ugcEnCirculacion; }

    public Integer getTotalPartidos() { return totalPartidos; }
    public void setTotalPartidos(Integer totalPartidos) { this.totalPartidos = totalPartidos; }

    public Integer getPartidosFinalizados() { return partidosFinalizados; }
    public void setPartidosFinalizados(Integer partidosFinalizados) { this.partidosFinalizados = partidosFinalizados; }

    public Boolean getSuccess() { return success; }
    public void setSuccess(Boolean success) { this.success = success; }
}