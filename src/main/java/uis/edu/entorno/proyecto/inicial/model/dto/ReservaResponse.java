package uis.edu.entorno.proyecto.inicial.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaResponse {
    private Integer id;
    private Integer usuarioId;
    private String nombreUsuario;
    private Integer canchaId;
    private String nombreCancha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaFin;

    private String estado;

    public ReservaResponse() {}

    public ReservaResponse(Integer id, Integer usuarioId, String nombreUsuario,
                           Integer canchaId, String nombreCancha,
                           LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.canchaId = canchaId;
        this.nombreCancha = nombreCancha;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    // Getters y setters...
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public Integer getCanchaId() { return canchaId; }
    public void setCanchaId(Integer canchaId) { this.canchaId = canchaId; }

    public String getNombreCancha() { return nombreCancha; }
    public void setNombreCancha(String nombreCancha) { this.nombreCancha = nombreCancha; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}