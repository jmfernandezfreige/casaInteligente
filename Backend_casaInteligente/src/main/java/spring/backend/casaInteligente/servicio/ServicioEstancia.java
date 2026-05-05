package spring.backend.casaInteligente.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.backend.casaInteligente.entidad.Estancia;
import spring.backend.casaInteligente.entidad.Vivienda;
import spring.backend.casaInteligente.repositorio.RepoEstancia;
import spring.backend.casaInteligente.repositorio.RepoVivienda;

@Service
public class ServicioEstancia {
    @Autowired
    private RepoEstancia repoEstancia;
    @Autowired
    private RepoVivienda repoVivienda;

    public Estancia creaEstancia(Long idVivienda, Estancia estanciaNueva) {
        Vivienda vivienda = repoVivienda.findById(idVivienda)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vivienda no encontrada"));

        estanciaNueva.setVivienda(vivienda);

        return repoEstancia.save(estanciaNueva);
    }

    public Estancia getEstancia(Long idEstancia) {
        return repoEstancia.findById(idEstancia)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estancia no encontrada"));
    }

    public Estancia modificaEstancia(Long idEstancia, Estancia estanciaModificada) {
        Estancia estancia = getEstancia(idEstancia);

        estancia.setNombre(estanciaModificada.getNombre());
        estancia.setPlanta(estanciaModificada.getPlanta());

        return repoEstancia.save(estancia);
    }

    public void borraEstancia(Long idEstancia) {
        repoEstancia.delete(getEstancia(idEstancia));
    }
}
