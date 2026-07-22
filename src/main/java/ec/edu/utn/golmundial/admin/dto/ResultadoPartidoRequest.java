package ec.edu.utn.golmundial.admin.dto;

public class ResultadoPartidoRequest {
    private Integer golesLocal;
    private Integer golesVisitante;
    private Long usuarioAdminId;
    private Long ganadorPenalesId;

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(Integer golesLocal) {
        this.golesLocal = golesLocal;
    }

    public Integer getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(Integer golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public Long getUsuarioAdminId() {
        return usuarioAdminId;
    }

    public void setUsuarioAdminId(Long usuarioAdminId) {
        this.usuarioAdminId = usuarioAdminId;
    }

    public Long getGanadorPenalesId() {
        return ganadorPenalesId;
    }

    public void setGanadorPenalesId(Long ganadorPenalesId) {
        this.ganadorPenalesId = ganadorPenalesId;
    }
}
