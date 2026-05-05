package spring.backend.casaInteligente.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.backend.casaInteligente.entidad.Usuario;
import spring.backend.casaInteligente.repositorio.RepoUsuario;

@Service
public class ServicioUsuario {
    @Autowired
    private RepoUsuario repoUsuario;

    public Usuario getUsuarioLogueado(Authentication authentication) {
        return repoUsuario.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"));
    }

    public Usuario registraUsuario(Usuario usuarioNuevo) {
        if (repoUsuario.findByEmail(usuarioNuevo.getEmail()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El email ya está en uso");
        }

        if (usuarioNuevo.getRol() == null) {
            usuarioNuevo.setRol(Usuario.Rol.USER);
        }

        return repoUsuario.save(usuarioNuevo);
    }

    public Usuario modificaUsuario(Authentication authentication, Usuario usuarioModificado) {
        Usuario usuario = getUsuarioLogueado(authentication);
        if (!usuario.getEmail().equals(usuarioModificado.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se puede cambiar el email del usuario");
        }

        if (usuarioModificado.getRol() != null) {
            usuario.setRol(usuarioModificado.getRol());
        }

        usuario.setNombre(usuarioModificado.getNombre());
        usuario.setContraseña(usuarioModificado.getContraseña());

        return repoUsuario.save(usuario);
    }

    public void borraUsuario(Authentication authentication) {
        Usuario usuario = getUsuarioLogueado(authentication);

        repoUsuario.delete(usuario);
    }

    //Para ADMIN
    public Usuario getUsuario(Long idUsuario) {
        return repoUsuario.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"));
    }
}
