package ec.edu.utn.golmundial.admin.dto;

public class PartidoDto {
    private Long id;
    private Integer numeroPartidoFifa;
    private String fase;
    private String grupo;

    private Long seleccionLocalId;
    private String seleccionLocalCodigo;
    private String seleccionLocalNombre;

    private Long seleccionVisitanteId;
    private String seleccionVisitanteCodigo;
    private String seleccionVisitanteNombre;

    private String fechaHoraUtc;
    private String fechaHoraEcuador;

    private Long sedeId;
    private String sedeNombre;
    private String ciudad;
    private String pais;

    private String estado;
    private Integer golesLocal;
    private Integer golesVisitante;

    private Long ganadorPenalesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroPartidoFifa() {
        return numeroPartidoFifa;
    }

    public void setNumeroPartidoFifa(Integer numeroPartidoFifa) {
        this.numeroPartidoFifa = numeroPartidoFifa;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Long getSeleccionLocalId() {
        return seleccionLocalId;
    }

    public void setSeleccionLocalId(Long seleccionLocalId) {
        this.seleccionLocalId = seleccionLocalId;
    }

    public String getSeleccionLocalCodigo() {
        return seleccionLocalCodigo;
    }

    public void setSeleccionLocalCodigo(String seleccionLocalCodigo) {
        this.seleccionLocalCodigo = seleccionLocalCodigo;
    }

    public String getSeleccionLocalNombre() {
        return seleccionLocalNombre;
    }

    public void setSeleccionLocalNombre(String seleccionLocalNombre) {
        this.seleccionLocalNombre = seleccionLocalNombre;
    }

    public Long getSeleccionVisitanteId() {
        return seleccionVisitanteId;
    }

    public void setSeleccionVisitanteId(Long seleccionVisitanteId) {
        this.seleccionVisitanteId = seleccionVisitanteId;
    }

    public String getSeleccionVisitanteCodigo() {
        return seleccionVisitanteCodigo;
    }

    public void setSeleccionVisitanteCodigo(String seleccionVisitanteCodigo) {
        this.seleccionVisitanteCodigo = seleccionVisitanteCodigo;
    }

    public String getSeleccionVisitanteNombre() {
        return seleccionVisitanteNombre;
    }

    public void setSeleccionVisitanteNombre(String seleccionVisitanteNombre) {
        this.seleccionVisitanteNombre = seleccionVisitanteNombre;
    }

    public String getFechaHoraUtc() {
        return fechaHoraUtc;
    }

    public void setFechaHoraUtc(String fechaHoraUtc) {
        this.fechaHoraUtc = fechaHoraUtc;
    }

    public String getFechaHoraEcuador() {
        return fechaHoraEcuador;
    }

    public void setFechaHoraEcuador(String fechaHoraEcuador) {
        this.fechaHoraEcuador = fechaHoraEcuador;
    }

    public Long getSedeId() {
        return sedeId;
    }

    public void setSedeId(Long sedeId) {
        this.sedeId = sedeId;
    }

    public String getSedeNombre() {
        return sedeNombre;
    }

    public void setSedeNombre(String sedeNombre) {
        this.sedeNombre = sedeNombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

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

    public Long getGanadorPenalesId() {
        return ganadorPenalesId;
    }

    public void setGanadorPenalesId(Long ganadorPenalesId) {
        this.ganadorPenalesId = ganadorPenalesId;
    }
}
