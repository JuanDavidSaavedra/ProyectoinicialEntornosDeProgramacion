package uis.edu.entorno.proyecto.inicial.repository;

import uis.edu.entorno.proyecto.inicial.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByUsuarioId(Integer usuarioId);
    List<Reserva> findByCanchaId(Integer canchaId);
    List<Reserva> findByFecha(LocalDate fecha);
    List<Reserva> findByEstado(String estado);
    List<Reserva> findByCanchaIdAndFecha(Integer canchaId, LocalDate fecha);
    boolean existsByCanchaIdAndFechaAndHoraInicioLessThanAndHoraFinGreaterThan(
            Integer canchaId, LocalDate fecha, LocalTime horaFin, LocalTime horaInicio);
}
