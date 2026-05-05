package spring.backend.casaInteligente.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.backend.casaInteligente.entidad.Dispositivo;
import spring.backend.casaInteligente.entidad.Estancia;
import spring.backend.casaInteligente.repositorio.RepoDispositivo;
import spring.backend.casaInteligente.repositorio.RepoEstancia;

@Service
public class ServicioDispositivo {
    @Autowired
    private RepoDispositivo repoDispositivo;
    @Autowired
    private RepoEstancia repoEstancia;

    public Dispositivo creaDispositivo(Long idEstancia, Dispositivo dispositivoNuevo) {
        Estancia estancia = repoEstancia.findById(idEstancia)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estancia no encontrada"));
        dispositivoNuevo.setEstancia(estancia);
        return repoDispositivo.save(dispositivoNuevo);
    }

    public Dispositivo getDispositivo(Long idDispositivo) {
        return repoDispositivo.findById(idDispositivo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dispositivo no encontrado"));
    }

    public Dispositivo modificaDispositivo(Long idDispositivo, Dispositivo dispositivoModificado) {
        Dispositivo dispositivo = getDispositivo(idDispositivo);

        dispositivo.setNombre(dispositivoModificado.getNombre());
        dispositivo.setEncendido(dispositivoModificado.getEncendido());
        dispositivo.setNivel(dispositivoModificado.getNivel());
        dispositivo.setTipo(dispositivoModificado.getTipo());

        return repoDispositivo.save(dispositivo);
    }

    public void borraDispositivo(Long idDispositivo) {
        repoDispositivo.delete(getDispositivo(idDispositivo));
    }
}
