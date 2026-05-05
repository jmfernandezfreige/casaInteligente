package spring.backend.casaInteligente.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.backend.casaInteligente.entidad.Dispositivo;
import spring.backend.casaInteligente.entidad.Estancia;

import java.util.List;

@Repository
public interface RepoDispositivo extends CrudRepository<Dispositivo,Long> {
    Lis<Dispositivo> findAll();
    List<Dispositivo> findByEncendido(Boolean encendido);
    List<Dispositivo> findByTipo(Dispositivo.Tipo tipo);
    Integer countByEstancia(Estancia estancia);
}
