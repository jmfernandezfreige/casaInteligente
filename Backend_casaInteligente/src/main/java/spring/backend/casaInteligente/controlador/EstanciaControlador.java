package spring.backend.casaInteligente.controlador;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.backend.casaInteligente.entidad.Estancia;
import spring.backend.casaInteligente.servicio.ServicioEstancia;

@RestController
@RequestMapping("/api/{idVivienda}/estancias")
@CrossOrigin
public class EstanciaControlador {
    @Autowired
    private ServicioEstancia servicioEstancia;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estancia creaEstancia(@PathVariable Long idVivienda, @Valid @RequestBody Estancia estanciaNueva) {
        return servicioEstancia.creaEstancia(idVivienda, estanciaNueva);
    }

    @GetMapping("/{idEstancia}")
    public Estancia getEstancia(@PathVariable Long idEstancia) {
        return servicioEstancia.getEstancia(idEstancia);
    }

    @PutMapping("/{idEstancia}")
    public Estancia modificaEstancia(@PathVariable Long idEstancia, @Valid @RequestBody Estancia estanciaModificada) {
        return servicioEstancia.modificaEstancia(idEstancia, estanciaModificada);
    }

    @DeleteMapping("/{idEstancia}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borraEstancia(@PathVariable Long idEstancia) {
        servicioEstancia.borraEstancia(idEstancia);
    }
}
