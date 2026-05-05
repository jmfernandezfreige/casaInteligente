package spring.backend.casaInteligente.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
@Table(name="DEVICES")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotNull(message="El nombre es obligatorio")
    private String nombre;

    @Column(name="device_on")
    private Boolean encendido;

    @Column(name="level")
    @PositiveOrZero(message="El nivel no puede ser negativo")
    @Max(value=100, message="El valor máximo es 100")
    private Double nivel;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Estancia estancia;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public enum Tipo {
        ILUMINACION,
        CLIMATIZACION,
        RIEGO,
        SEGURIDAD,
        MULTIMEDIA
    };
}
