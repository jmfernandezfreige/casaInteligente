package spring.backend.casaInteligente.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.backend.casaInteligente.entidad.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoUsuario extends CrudRepository<Usuario,Long> {
    List<Usuario> findAll();
    Optional<Usuario> findByEmail(String email);

}
