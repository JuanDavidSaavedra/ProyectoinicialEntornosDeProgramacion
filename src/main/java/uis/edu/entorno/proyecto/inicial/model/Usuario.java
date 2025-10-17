package uis.edu.entorno.proyecto.inicial.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cedula", unique = true, nullable = false, length = 20)
    private String cedula;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "usuario", unique = true, nullable = false, length = 50)
    private String usuario;

    @Column(name = "contraseña", nullable = false, length = 255)
    private String contraseña;

    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructores
    public Usuario() {
        this.createdAt = LocalDateTime.now();
    }

    public Usuario(String cedula, String nombre, String email, String usuario,
                   String contraseña, String rol) {
        this();
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}