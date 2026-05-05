package spring.backend.casaInteligente.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name="HOUSES")
public class Vivienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotNull(message="El nombre es oboligatorio")
    private String nombre;

    @Column(name="location")
    private String ubicacion;

    @Column(name="floors")
    @Positive(message="El número de plantas debe ser positivo")
    private Integer plantas;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Usuario propietario;

    @OneToMany(mappedBy = "vivienda", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Estancia> estancias;
}
