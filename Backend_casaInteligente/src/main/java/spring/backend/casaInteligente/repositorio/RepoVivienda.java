package spring.backend.casaInteligente.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.backend.casaInteligente.entidad.Usuario;
import spring.backend.casaInteligente.entidad.Vivienda;

import java.util.List;

@Repository
public interface RepoVivienda extends CrudRepository<Vivienda,Long> {
    List<Vivienda> findAll();
    List<Vivienda> findByPropietario(Usuario propietario);
    Integer countByPropietario(Usuario propietario);
}
