package uis.edu.entorno.proyecto.inicial.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "canchas")
public class Cancha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "deporte", nullable = false, length = 50)
    private String deporte;

    @Column(name = "ubicacion", nullable = false, length = 150)
    private String ubicacion;

    @Column(name = "precio_hora", nullable = false)
    private Double precioHora;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "hora_apertura", nullable = false)
    private LocalTime horaApertura;

    @Column(name = "hora_cierre", nullable = false)
    private LocalTime horaCierre;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    // Constructores
    public Cancha() {
        this.creadoEn = LocalDateTime.now();
        this.estado = "ACTIVA";
        this.capacidad = 10;
        this.horaApertura = LocalTime.of(5, 0); // 5:00 am por defecto
        this.horaCierre = LocalTime.of(22, 0); // 10:00 pm por defecto
    }

    public Cancha(String nombre, String deporte, String ubicacion, Double precioHora, Integer capacidad, LocalTime horaApertura, LocalTime horaCierre) {
        this();
        this.nombre = nombre;
        this.deporte = deporte;
        this.ubicacion = ubicacion;
        this.precioHora = precioHora;
        this.capacidad = capacidad;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDeporte() { return deporte; }
    public void setDeporte(String deporte) { this.deporte = deporte; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public Double getPrecioHora() { return precioHora; }
    public void setPrecioHora(Double precioHora) { this.precioHora = precioHora; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public LocalTime getHoraApertura() { return horaApertura; }
    public void setHoraApertura(LocalTime horaApertura) { this.horaApertura = horaApertura; }

    public LocalTime getHoraCierre() { return horaCierre; }
    public void setHoraCierre(LocalTime horaCierre) { this.horaCierre = horaCierre; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(LocalDateTime creadoEn) { this.creadoEn = creadoEn; }
}