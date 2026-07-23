package ec.edu.utn.golmundial.admin.dto;

public class GolCoinMetricasDto {
    private double totalUgcEnCirculacion;
    private int totalBilleteras;
    private String fechaConsultaUtc;

    public double getTotalUgcEnCirculacion() {
        return totalUgcEnCirculacion;
    }

    public void setTotalUgcEnCirculacion(double totalUgcEnCirculacion) {
        this.totalUgcEnCirculacion = totalUgcEnCirculacion;
    }

    public int getTotalBilleteras() {
        return totalBilleteras;
    }

    public void setTotalBilleteras(int totalBilleteras) {
        this.totalBilleteras = totalBilleteras;
    }

    public String getFechaConsultaUtc() {
        return fechaConsultaUtc;
    }

    public void setFechaConsultaUtc(String fechaConsultaUtc) {
        this.fechaConsultaUtc = fechaConsultaUtc;
    }
}