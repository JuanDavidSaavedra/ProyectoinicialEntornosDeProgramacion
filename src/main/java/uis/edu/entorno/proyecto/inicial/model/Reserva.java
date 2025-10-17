package uis.edu.entorno.proyecto.inicial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cancha_id", nullable = false)
    private Cancha cancha;

    @Column(name = "fecha", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Column(name = "hora_inicio", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaFin;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "creado_en")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creadoEn;

    public Reserva() {
        this.creadoEn = LocalDateTime.now();
        this.estado = "ACTIVA";
    }

    public Reserva(Usuario usuario, Cancha cancha, LocalDate fecha,
                   LocalTime horaInicio, LocalTime horaFin) {
        this();
        this.usuario = usuario;
        this.cancha = cancha;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // Getters y setters...
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Cancha getCancha() { return cancha; }
    public void setCancha(Cancha cancha) { this.cancha = cancha; }

    public java.time.LocalDate getFecha() { return fecha; }
    public void setFecha(java.time.LocalDate fecha) { this.fecha = fecha; }

    public java.time.LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(java.time.LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public java.time.LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(java.time.LocalTime horaFin) { this.horaFin = horaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public java.time.LocalDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(java.time.LocalDateTime creadoEn) { this.creadoEn = creadoEn; }
}
