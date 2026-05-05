package spring.backend.casaInteligente.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.backend.casaInteligente.entidad.Usuario;
import spring.backend.casaInteligente.entidad.Vivienda;
import spring.backend.casaInteligente.repositorio.RepoUsuario;
import spring.backend.casaInteligente.repositorio.RepoVivienda;
import org.springframework.security.core.Authentication;

@Service
public class ServicioVivienda {
    @Autowired
    private RepoVivienda repoVivienda;
    @Autowired
    private RepoUsuario repoUsuario;

    public Vivienda creaVivienda(Authentication authentication, Vivienda viviendaNueva) {
        Usuario usuario = repoUsuario.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no autenticado"));

        viviendaNueva.setPropietario(usuario);
        return repoVivienda.save(viviendaNueva);
    }

    public Vivienda getVivienda(Long idVivienda) {
        return repoVivienda.findById(idVivienda)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No existe la vivienda"));
    }

    public Vivienda modificaVivienda(Long idVivienda, Vivienda viviendaModificada) {
        Vivienda vivienda = getVivienda(idVivienda);

        vivienda.setNombre(viviendaModificada.getNombre());
        vivienda.setPlantas(viviendaModificada.getPlantas());
        vivienda.setUbicacion(viviendaModificada.getUbicacion());

        return repoVivienda.save(vivienda);
    }

    public void borraVivienda(Long idVivienda) {
        repoVivienda.delete(getVivienda(idVivienda));
    }

}
