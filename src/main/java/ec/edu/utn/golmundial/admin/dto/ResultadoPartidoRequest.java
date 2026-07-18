package ec.edu.utn.golmundial.admin.dto;

public class ResultadoPartidoRequest {
    private Integer golesLocal;
    private Integer golesVisitante;
    private Long usuarioAdminId;

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
}
