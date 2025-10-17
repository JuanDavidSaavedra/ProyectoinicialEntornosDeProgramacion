package uis.edu.entorno.proyecto.inicial.controller;

import uis.edu.entorno.proyecto.inicial.model.Usuario;
import uis.edu.entorno.proyecto.inicial.model.dto.ApiResponse;
import uis.edu.entorno.proyecto.inicial.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.findAll();
            return ResponseEntity.ok(ApiResponse.success("Usuarios obtenidos exitosamente", usuarios));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al obtener usuarios: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUsuarioById(@PathVariable Integer id) {
        try {
            Optional<Usuario> usuario = usuarioService.findById(id);
            if (usuario.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success("Usuario encontrado", usuario.get()));
            } else {
                return ResponseEntity.ok(ApiResponse.error("Usuario no encontrado"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al buscar usuario: " + e.getMessage()));
        }
    }

    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<ApiResponse> getUsuarioByCedula(@PathVariable String cedula) {
        try {
            Optional<Usuario> usuario = usuarioService.findByCedula(cedula);
            if (usuario.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success("Usuario encontrado", usuario.get()));
            } else {
                return ResponseEntity.ok(ApiResponse.error("Usuario no encontrado"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al buscar usuario: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createUsuario(@RequestBody Usuario usuario) {
        try {
            // Validar campos requeridos
            if (usuario.getCedula() == null || usuario.getCedula().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("La cédula es requerida"));
            }
            if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("El nombre es requerido"));
            }
            if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("El email es requerido"));
            }
            if (usuario.getUsuario() == null || usuario.getUsuario().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("El nombre de usuario es requerido"));
            }
            if (usuario.getContraseña() == null || usuario.getContraseña().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("La contraseña es requerida"));
            }
            if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("El rol es requerido"));
            }

            // Verificar duplicados
            if (usuarioService.existsByCedula(usuario.getCedula())) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Ya existe un usuario con esta cédula"));
            }
            if (usuarioService.existsByEmail(usuario.getEmail())) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Ya existe un usuario con este email"));
            }
            if (usuarioService.existsByUsuario(usuario.getUsuario())) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Ya existe un usuario con este nombre de usuario"));
            }

            Usuario nuevoUsuario = usuarioService.create(usuario);
            return ResponseEntity.ok(ApiResponse.success("Usuario creado exitosamente", nuevoUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al crear usuario: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            if (!usuarioService.findById(id).isPresent()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Usuario no encontrado"));
            }

            // Validar campos requeridos
            if (usuario.getCedula() == null || usuario.getCedula().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("La cédula es requerida"));
            }
            if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("El nombre es requerido"));
            }
            if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("El email es requerido"));
            }
            if (usuario.getUsuario() == null || usuario.getUsuario().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("El nombre de usuario es requerido"));
            }
            if (usuario.getContraseña() == null || usuario.getContraseña().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("La contraseña es requerida"));
            }
            if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("El rol es requerido"));
            }

            // Verificar duplicados excluyendo el usuario actual
            Optional<Usuario> usuarioPorCedula = usuarioService.findByCedula(usuario.getCedula());
            if (usuarioPorCedula.isPresent() && !usuarioPorCedula.get().getId().equals(id)) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Ya existe un usuario con esta cédula"));
            }

            Optional<Usuario> usuarioPorEmail = usuarioService.findByEmail(usuario.getEmail());
            if (usuarioPorEmail.isPresent() && !usuarioPorEmail.get().getId().equals(id)) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Ya existe un usuario con este email"));
            }

            Optional<Usuario> usuarioPorUsuario = usuarioService.findByUsuario(usuario.getUsuario());
            if (usuarioPorUsuario.isPresent() && !usuarioPorUsuario.get().getId().equals(id)) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Ya existe un usuario con este nombre de usuario"));
            }

            usuario.setId(id);
            Usuario usuarioActualizado = usuarioService.update(usuario);
            return ResponseEntity.ok(ApiResponse.success("Usuario actualizado exitosamente", usuarioActualizado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al actualizar usuario: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUsuario(@PathVariable Integer id) {
        try {
            if (!usuarioService.findById(id).isPresent()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Usuario no encontrado"));
            }
            usuarioService.delete(id);
            return ResponseEntity.ok(ApiResponse.success("Usuario eliminado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al eliminar usuario: " + e.getMessage()));
        }
    }
}
