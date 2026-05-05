package spring.backend.casaInteligente.controlador;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import spring.backend.casaInteligente.entidad.Vivienda;
import spring.backend.casaInteligente.servicio.ServicioVivienda;

@RestController
@RequestMapping("/api/viviendas")
@CrossOrigin
public class ViviendaControlador {
    @Autowired
    private ServicioVivienda servicioVivienda;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vivienda creaVivienda(Authentication authentication, @Valid @RequestBody Vivienda viviendaNueva) {
        return servicioVivienda.creaVivienda(authentication, viviendaNueva);
    }

    @GetMapping("/{idVivienda}")
    public Vivienda getVivienda(@PathVariable Long idVivienda) {
        return servicioVivienda.getVivienda(idVivienda);
    }

    @PutMapping("/{idVivienda}")
    public Vivienda modificaVivienda(@PathVariable Long idVivienda, @Valid @RequestBody Vivienda viviendaModificada) {
        return servicioVivienda.modificaVivienda(idVivienda, viviendaModificada);
    }

    @DeleteMapping("/{idVivienda}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borraVivienda(@PathVariable Long idVivienda) {
        servicioVivienda.borraVivienda(idVivienda);
    }
}
