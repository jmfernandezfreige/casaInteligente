package spring.backend.casaInteligente.controlador;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import spring.backend.casaInteligente.entidad.Usuario;
import spring.backend.casaInteligente.servicio.ServicioUsuario;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class UsuarioControlador {
    @Autowired
    private ServicioUsuario servicioUsuario;

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario registraUsuario(@Valid @RequestBody Usuario usuarioNuevo) {
        return servicioUsuario.registraUsuario(usuarioNuevo);
    }

    @GetMapping("/auth/me")
    public Usuario getAutenticacion(Authentication authentication) {
        return servicioUsuario.getUsuarioLogueado(authentication);
    }

    @GetMapping("/usuarios/{idUsuario}")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario getUsuario(@PathVariable Long idUsuario) {
        return servicioUsuario.getUsuario(idUsuario);
    }

    @PutMapping("/usuarios/me")
    public Usuario modificaUsuario(Authentication authentication, @Valid @RequestBody Usuario usuarioModificado) {
        return servicioUsuario.modificaUsuario(authentication, usuarioModificado);
    }

    @DeleteMapping("usuarios/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borraUsuario(Authentication authentication) {
        servicioUsuario.borraUsuario(authentication);
    }
}
