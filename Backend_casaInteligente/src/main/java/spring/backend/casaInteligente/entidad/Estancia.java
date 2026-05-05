package spring.backend.casaInteligente.entidad;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name="ROOMS")
public class Estancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name="floors")
    @NotNull(message="La planta debe especificarse, si solo hay una indique '0'")
    private Integer planta;

    @ManyToOne
    @JoinColumn(name="house_id")
    private Vivienda vivienda;

    @OneToMany(mappedBy="estancia", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Dispositivo> dispositivos;
}
