package uis.edu.entorno.proyecto.inicial.controller;

import uis.edu.entorno.proyecto.inicial.model.Reserva;
import uis.edu.entorno.proyecto.inicial.model.dto.ApiResponse;
import uis.edu.entorno.proyecto.inicial.model.dto.ReservaRequest;
import uis.edu.entorno.proyecto.inicial.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllReservas() {
        try {
            List<Reserva> reservas = reservaService.findAll();
            return ResponseEntity.ok(ApiResponse.success("Reservas obtenidas exitosamente", reservas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al obtener reservas: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getReservaById(@PathVariable Integer id) {
        try {
            Optional<Reserva> reserva = reservaService.findById(id);
            if (reserva.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success("Reserva encontrada", reserva.get()));
            } else {
                return ResponseEntity.ok(ApiResponse.error("Reserva no encontrada"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al buscar reserva: " + e.getMessage()));
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ApiResponse> getReservasByUsuario(@PathVariable Integer usuarioId) {
        try {
            List<Reserva> reservas = reservaService.findByUsuarioId(usuarioId);
            return ResponseEntity.ok(ApiResponse.success("Reservas obtenidas exitosamente", reservas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al obtener reservas: " + e.getMessage()));
        }
    }

    @GetMapping("/cancha/{canchaId}")
    public ResponseEntity<ApiResponse> getReservasByCancha(@PathVariable Integer canchaId) {
        try {
            List<Reserva> reservas = reservaService.findByCanchaId(canchaId);
            return ResponseEntity.ok(ApiResponse.success("Reservas obtenidas exitosamente", reservas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al obtener reservas: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createReserva(@RequestBody ReservaRequest reservaRequest) {
        try {
            Reserva nuevaReserva = reservaService.create(reservaRequest);
            return ResponseEntity.ok(ApiResponse.success("Reserva creada exitosamente", nuevaReserva));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al crear reserva: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<ApiResponse> updateEstadoReserva(@PathVariable Integer id, @RequestBody String estado) {
        try {
            Reserva reservaActualizada = reservaService.updateEstado(id, estado);
            return ResponseEntity.ok(ApiResponse.success("Estado de reserva actualizado exitosamente", reservaActualizada));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al actualizar reserva: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteReserva(@PathVariable Integer id) {
        try {
            if (!reservaService.findById(id).isPresent()) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Reserva no encontrada"));
            }
            reservaService.delete(id);
            return ResponseEntity.ok(ApiResponse.success("Reserva eliminada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al eliminar reserva: " + e.getMessage()));
        }
    }
}