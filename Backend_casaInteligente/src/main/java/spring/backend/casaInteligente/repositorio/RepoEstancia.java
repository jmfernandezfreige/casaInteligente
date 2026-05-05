package spring.backend.casaInteligente.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.backend.casaInteligente.entidad.Estancia;
import spring.backend.casaInteligente.entidad.Vivienda;

import java.util.List;

@Repository
public interface RepoEstancia extends CrudRepository<Estancia,Long> {
    List<Estancia> findAll();
    List<Estancia> findByPlanta(Integer planta);
    Integer countByVivienda(Vivienda vivienda);

    Long id(Long id);
}
