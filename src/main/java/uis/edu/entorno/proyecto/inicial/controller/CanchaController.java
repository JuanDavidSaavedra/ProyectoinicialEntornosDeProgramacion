package uis.edu.entorno.proyecto.inicial.controller;

import uis.edu.entorno.proyecto.inicial.model.Cancha;
import uis.edu.entorno.proyecto.inicial.model.dto.ApiResponse;
import uis.edu.entorno.proyecto.inicial.service.ICanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/canchas")
@CrossOrigin(origins = "*")
public class CanchaController {

    @Autowired
    private ICanchaService canchaService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCanchas() {
        try {
            List<Cancha> canchas = canchaService.findAll();
            return ResponseEntity.ok(ApiResponse.success("Canchas obtenidas exitosamente", canchas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al obtener canchas: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCanchaById(@PathVariable Integer id) {
        try {
            Optional<Cancha> cancha = canchaService.findById(id);
            if (cancha.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success("Cancha encontrada", cancha.get()));
            } else {
                return ResponseEntity.ok(ApiResponse.error("Cancha no encontrada"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al buscar cancha: " + e.getMessage()));
        }
    }

    @GetMapping("/deporte/{deporte}")
    public ResponseEntity<ApiResponse> getCanchasByDeporte(@PathVariable String deporte) {
        try {
            List<Cancha> canchas = canchaService.findByDeporte(deporte);
            return ResponseEntity.ok(ApiResponse.success("Canchas obtenidas exitosamente", canchas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al obtener canchas: " + e.getMessage()));
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<ApiResponse> getCanchasByEstado(@PathVariable String estado) {
        try {
            List<Cancha> canchas = canchaService.findByEstado(estado);
            return ResponseEntity.ok(ApiResponse.success("Canchas obtenidas exitosamente", canchas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al obtener canchas: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCancha(@RequestBody Cancha cancha) {
        try {
            Cancha nuevaCancha = canchaService.create(cancha);
            return ResponseEntity.ok(ApiResponse.success("Cancha creada exitosamente", nuevaCancha));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al crear cancha: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCancha(@PathVariable Integer id, @RequestBody Cancha cancha) {
        try {
            if (!canchaService.findById(id).isPresent()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Cancha no encontrada"));
            }
            cancha.setId(id);
            Cancha canchaActualizada = canchaService.update(cancha);
            return ResponseEntity.ok(ApiResponse.success("Cancha actualizada exitosamente", canchaActualizada));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al actualizar cancha: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCancha(@PathVariable Integer id) {
        try {
            if (!canchaService.findById(id).isPresent()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Cancha no encontrada"));
            }
            canchaService.delete(id);
            return ResponseEntity.ok(ApiResponse.success("Cancha eliminada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al eliminar cancha: " + e.getMessage()));
        }
    }
}