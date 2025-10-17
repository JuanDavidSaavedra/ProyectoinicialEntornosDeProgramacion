package uis.edu.entorno.proyecto.inicial.service.impl;

import uis.edu.entorno.proyecto.inicial.model.Cancha;
import uis.edu.entorno.proyecto.inicial.model.Reserva;
import uis.edu.entorno.proyecto.inicial.repository.CanchaRepository;
import uis.edu.entorno.proyecto.inicial.repository.ReservaRepository;
import uis.edu.entorno.proyecto.inicial.service.ICanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CanchaServiceImpl implements ICanchaService {

    @Autowired
    private CanchaRepository canchaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Cancha> findAll() {
        return canchaRepository.findAll();
    }

    @Override
    public Optional<Cancha> findById(Integer id) {
        return canchaRepository.findById(id);
    }

    @Override
    public List<Cancha> findByDeporte(String deporte) {
        return canchaRepository.findByDeporte(deporte);
    }

    @Override
    public List<Cancha> findByEstado(String estado) {
        return canchaRepository.findByEstado(estado);
    }

    @Override
    public Cancha create(Cancha cancha) {
        return canchaRepository.save(cancha);
    }

    @Override
    public Cancha update(Cancha cancha) {
        return canchaRepository.save(cancha);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // Eliminar todas las reservas asociadas a la cancha primero
        List<Reserva> reservasCancha = reservaRepository.findByCanchaId(id);
        reservaRepository.deleteAll(reservasCancha);

        // Luego eliminar la cancha
        canchaRepository.deleteById(id);
    }
}