package uis.edu.entorno.proyecto.inicial.service.impl;

import uis.edu.entorno.proyecto.inicial.model.Reserva;
import uis.edu.entorno.proyecto.inicial.model.Usuario;
import uis.edu.entorno.proyecto.inicial.model.Cancha;
import uis.edu.entorno.proyecto.inicial.model.dto.ReservaRequest;
import uis.edu.entorno.proyecto.inicial.repository.ReservaRepository;
import uis.edu.entorno.proyecto.inicial.repository.UsuarioRepository;
import uis.edu.entorno.proyecto.inicial.repository.CanchaRepository;
import uis.edu.entorno.proyecto.inicial.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements IReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CanchaRepository canchaRepository;

    // Formatter para horas en formato 12h
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Optional<Reserva> findById(Integer id) {
        return reservaRepository.findById(id);
    }

    @Override
    public List<Reserva> findByUsuarioId(Integer usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Reserva> findByCanchaId(Integer canchaId) {
        return reservaRepository.findByCanchaId(canchaId);
    }

    @Override
    @Transactional
    public Reserva create(ReservaRequest reservaRequest) {
        Usuario usuario = usuarioRepository.findById(reservaRequest.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Cancha cancha = canchaRepository.findById(reservaRequest.getCanchaId())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        // Validar horario de atención de la cancha
        if (!isDentroHorarioAtencion(cancha, reservaRequest.getHoraInicio(), reservaRequest.getHoraFin())) {
            String horaAperturaFormatted = formatTimeTo12Hours(cancha.getHoraApertura());
            String horaCierreFormatted = formatTimeTo12Hours(cancha.getHoraCierre());
            throw new RuntimeException("El horario de reserva está fuera del horario de atención de la cancha. " +
                    "Horario de atención: " + horaAperturaFormatted + " - " + horaCierreFormatted);
        }

        // Verificar capacidad de la cancha
        if (!isCanchaDisponible(reservaRequest.getCanchaId(), reservaRequest.getFecha(),
                reservaRequest.getHoraInicio(), reservaRequest.getHoraFin())) {
            throw new RuntimeException("Capacidad máxima alcanzada para esta cancha en el horario seleccionado. Reserve otra cancha o cambie la hora.");
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setCancha(cancha);
        reserva.setFecha(reservaRequest.getFecha());
        reserva.setHoraInicio(reservaRequest.getHoraInicio());
        reserva.setHoraFin(reservaRequest.getHoraFin());
        reserva.setEstado(reservaRequest.getEstado() != null ? reservaRequest.getEstado() : "ACTIVA");

        return reservaRepository.save(reserva);
    }

    @Override
    @Transactional
    public Reserva update(Integer id, ReservaRequest reservaRequest) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        Cancha cancha = canchaRepository.findById(reservaRequest.getCanchaId())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        // Validar horario de atención de la cancha
        if (!isDentroHorarioAtencion(cancha, reservaRequest.getHoraInicio(), reservaRequest.getHoraFin())) {
            String horaAperturaFormatted = formatTimeTo12Hours(cancha.getHoraApertura());
            String horaCierreFormatted = formatTimeTo12Hours(cancha.getHoraCierre());
            throw new RuntimeException("El horario de reserva está fuera del horario de atención de la cancha. " +
                    "Horario de atención: " + horaAperturaFormatted + " - " + horaCierreFormatted);
        }

        // Verificar capacidad de la cancha (excluyendo la reserva actual)
        if (!isCanchaDisponibleForUpdate(id, reservaRequest.getCanchaId(), reservaRequest.getFecha(),
                reservaRequest.getHoraInicio(), reservaRequest.getHoraFin())) {
            throw new RuntimeException("Capacidad máxima alcanzada para esta cancha en el horario seleccionado. Reserve otra cancha o cambie la hora.");
        }

        reserva.setUsuario(usuarioRepository.findById(reservaRequest.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        reserva.setCancha(cancha);
        reserva.setFecha(reservaRequest.getFecha());
        reserva.setHoraInicio(reservaRequest.getHoraInicio());
        reserva.setHoraFin(reservaRequest.getHoraFin());
        reserva.setEstado(reservaRequest.getEstado());

        return reservaRepository.save(reserva);
    }

    // Metodo auxiliar para formatear horas a formato 12h
    private String formatTimeTo12Hours(LocalTime time) {
        return time.format(timeFormatter).replace("AM", "a. m.").replace("PM", "p. m.");
    }

    private boolean isDentroHorarioAtencion(Cancha cancha, LocalTime horaInicio, LocalTime horaFin) {
        return !horaInicio.isBefore(cancha.getHoraApertura()) &&
                !horaFin.isAfter(cancha.getHoraCierre()) &&
                !horaInicio.isAfter(horaFin);
    }

    private boolean isCanchaDisponible(Integer canchaId, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        Cancha cancha = canchaRepository.findById(canchaId)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        List<Reserva> reservasSolapadas = reservaRepository
                .findByCanchaIdAndFechaAndHoraInicioLessThanAndHoraFinGreaterThanAndEstadoNot(
                        canchaId, fecha, horaFin, horaInicio, "CANCELADA");

        return reservasSolapadas.size() < cancha.getCapacidad();
    }

    private boolean isCanchaDisponibleForUpdate(Integer reservaId, Integer canchaId, LocalDate fecha,
                                                LocalTime horaInicio, LocalTime horaFin) {
        Cancha cancha = canchaRepository.findById(canchaId)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        List<Reserva> reservasSolapadas = reservaRepository
                .findByCanchaIdAndFechaAndHoraInicioLessThanAndHoraFinGreaterThanAndEstadoNotAndIdNot(
                        canchaId, fecha, horaFin, horaInicio, "CANCELADA", reservaId);

        return reservasSolapadas.size() < cancha.getCapacidad();
    }

    @Override
    public void actualizarEstadosAutomaticamente() {
        List<Reserva> reservasActivas = reservaRepository.findByEstado("ACTIVA");
        LocalDate hoy = LocalDate.now();

        for (Reserva reserva : reservasActivas) {
            if (reserva.getFecha().isBefore(hoy) ||
                    (reserva.getFecha().equals(hoy) && reserva.getHoraFin().isBefore(LocalTime.now()))) {
                reserva.setEstado("FINALIZADA");
                reservaRepository.save(reserva);
            }
        }
    }

    @Override
    public boolean isCanchaDisponible(Integer canchaId, String fecha, String horaInicio, String horaFin) {
        return true;
    }

    @Override
    public Reserva updateEstado(Integer id, String estado) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setEstado(estado);
        return reservaRepository.save(reserva);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        reservaRepository.deleteById(id);
        reasignarIdsReservas();
    }

    private void reasignarIdsReservas() {
        List<Reserva> reservas = reservaRepository.findAllByOrderByIdAsc();
        int nuevoId = 1;
        for (Reserva reserva : reservas) {
            if (!reserva.getId().equals(nuevoId)) {
                Reserva nuevaReserva = new Reserva();
                nuevaReserva.setUsuario(reserva.getUsuario());
                nuevaReserva.setCancha(reserva.getCancha());
                nuevaReserva.setFecha(reserva.getFecha());
                nuevaReserva.setHoraInicio(reserva.getHoraInicio());
                nuevaReserva.setHoraFin(reserva.getHoraFin());
                nuevaReserva.setEstado(reserva.getEstado());
                nuevaReserva.setCreadoEn(reserva.getCreadoEn());

                reservaRepository.save(nuevaReserva);
                reservaRepository.delete(reserva);
            }
            nuevoId++;
        }
    }
}
