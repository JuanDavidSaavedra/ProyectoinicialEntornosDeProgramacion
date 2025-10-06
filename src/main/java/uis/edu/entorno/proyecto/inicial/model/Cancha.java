package uis.edu.entorno.proyecto.inicial.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    // Constructores
    public Cancha() {
        this.creadoEn = LocalDateTime.now();
        this.estado = "ACTIVA";
    }

    public Cancha(String nombre, String deporte, String ubicacion, Double precioHora) {
        this();
        this.nombre = nombre;
        this.deporte = deporte;
        this.ubicacion = ubicacion;
        this.precioHora = precioHora;
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

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(LocalDateTime creadoEn) { this.creadoEn = creadoEn; }
}