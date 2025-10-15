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
    public Reserva create(ReservaRequest reservaRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(reservaRequest.getUsuarioId());
        Optional<Cancha> canchaOpt = canchaRepository.findById(reservaRequest.getCanchaId());

        if (usuarioOpt.isEmpty() || canchaOpt.isEmpty()) {
            throw new RuntimeException("Usuario o cancha no encontrados");
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuarioOpt.get());
        reserva.setCancha(canchaOpt.get());
        reserva.setFecha(reservaRequest.getFecha());
        reserva.setHoraInicio(reservaRequest.getHoraInicio());
        reserva.setHoraFin(reservaRequest.getHoraFin());
        reserva.setEstado(reservaRequest.getEstado());

        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva update(Integer id, ReservaRequest reservaRequest) {
        // Buscar la reserva existente
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        // Buscar el nuevo usuario (si aplica)
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(reservaRequest.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Buscar la nueva cancha (si aplica)
        Optional<Cancha> canchaOpt = canchaRepository.findById(reservaRequest.getCanchaId());
        if (canchaOpt.isEmpty()) {
            throw new RuntimeException("Cancha no encontrada");
        }

        // Actualizar campos
        reserva.setUsuario(usuarioOpt.get());
        reserva.setCancha(canchaOpt.get());
        reserva.setFecha(reservaRequest.getFecha());
        reserva.setHoraInicio(reservaRequest.getHoraInicio());
        reserva.setHoraFin(reservaRequest.getHoraFin());
        reserva.setEstado(reservaRequest.getEstado());

        // Guardar cambios
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva updateEstado(Integer id, String estado) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(id);
        if (reservaOpt.isPresent()) {
            Reserva reserva = reservaOpt.get();
            reserva.setEstado(estado);
            return reservaRepository.save(reserva);
        }
        throw new RuntimeException("Reserva no encontrada");
    }

    @Override
    public void delete(Integer id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public boolean isCanchaDisponible(Integer canchaId, String fecha, String horaInicio, String horaFin) {
        // Implementación simplificada - en producción validar superposición de horarios
        return true;
    }
}
