package ec.edu.utn.golmundial.admin.dto;

public class AuditoriaDto {
    private Long id;
    private Long usuarioId;
    private String accion;
    private String entidad;
    private Long entidadId;
    private String fechaHoraUtc;
    private String detalle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public Long getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(Long entidadId) {
        this.entidadId = entidadId;
    }

    public String getFechaHoraUtc() {
        return fechaHoraUtc;
    }

    public void setFechaHoraUtc(String fechaHoraUtc) {
        this.fechaHoraUtc = fechaHoraUtc;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
