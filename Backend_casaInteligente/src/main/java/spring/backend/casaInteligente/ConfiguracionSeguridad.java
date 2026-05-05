package spring.backend.casaInteligente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import spring.backend.casaInteligente.entidad.Usuario;
import spring.backend.casaInteligente.repositorio.RepoUsuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {
    @Autowired
    RepoUsuario repoUsuario;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        //Para peticiones cross origin
        http.cors(org.springframework.security.config.Customizer.withDefaults());

        http.
                authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register").permitAll()
                        .anyRequest().authenticated()
                );

        //Autenticación básica HTTP
        http.httpBasic(httpBasic -> {});

        return http.build();
    }

    //Definir encriptación de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Definir USUARIOS
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario u = repoUsuario.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            return User.builder()
                    .username(u.getEmail())
                    .password(u.getContraseña())
                    .roles(u.getRol().name())
                    .build();
        };
    }
}
