package spring.backend.casaInteligente.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name="USERS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotNull(message="El nombre es obligatorio")
    private String nombre;

    @Column(unique = true)
    @Email(message="Formato de email incorrecto")
    private String email;

    @Column(name="password")
    @NotBlank(message="La contraseña no puede estar vacía")
    @Size(min=6, message="El tamaño mínimo de contraseña es de 6 caracteres")
    private String contraseña;

    @OneToMany(mappedBy="propietario", cascade=CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Vivienda> viviendas;

    @Column(name="role")
    @NotNull(message="El Rol no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        ADMIN,
        USER
    };
}

