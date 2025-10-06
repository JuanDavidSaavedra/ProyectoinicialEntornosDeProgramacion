package uis.edu.entorno.proyecto.inicial.service;

import uis.edu.entorno.proyecto.inicial.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Integer id);
    Optional<Usuario> findByCedula(String cedula);
    Optional<Usuario> findByEmail(String email);
    Usuario create(Usuario usuario);
    Usuario update(Usuario usuario);
    void delete(Integer id);
    boolean existsByCedula(String cedula);
    boolean existsByEmail(String email);
}