package uis.edu.entorno.proyecto.inicial.service;

import uis.edu.entorno.proyecto.inicial.model.Cancha;
import java.util.List;
import java.util.Optional;

public interface ICanchaService {
    List<Cancha> findAll();
    Optional<Cancha> findById(Integer id);
    List<Cancha> findByDeporte(String deporte);
    List<Cancha> findByEstado(String estado);
    Cancha create(Cancha cancha);
    Cancha update(Cancha cancha);
    void delete(Integer id);
}