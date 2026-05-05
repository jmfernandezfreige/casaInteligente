package spring.backend.casaInteligente.controlador;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.backend.casaInteligente.entidad.Dispositivo;

@RestController
@RequestMapping("/api/{idEstancia}/dispositivos")
@CrossOrigin
public class DispositivoControlador {
    @Autowired
    private ServicioDispositivo servicioDispositivo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo creaDispositivo(@PathVariable Long idEstancia, @Valid @RequestBody Dispositivo dispositivoNuevo) {
        return servicioDispositivo.creaDispositivo(idEstancia, dispositivoNuevo);
    }

    @GetMapping("/{idDispositivo}")
    public Dispositivo getDispositivo(@PathVariable Long idDispositivo) {
        return servicioDispositivo.getDispositivo(idDispositivo);
    }

    @PatchMapping("/{idDispositivo}")
    public Dispositivo modificaDispositivo(@PathVariable Long idDispositivo, @Valid @RequestBody Dispositivo dispositivoModificado) {
        return servicioDispositivo.modificaDispositivo(idDispositivo, dispositivoModificado);
    }

    @DeleteMapping("/{idDispositivo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borraDispositivo(@PathVariable Long idDispositivo) {
        servicioDispositivo.borraDispositivo(idDispositivo);
    }
}
