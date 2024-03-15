
package com.proyecto.domain;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Long idMascota;

    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String alergias;
    private String rutaImagen;
    private boolean activo;

    @Column(name = "condiciones_salud")
    private String condicionesSalud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Usuario propietario;
}
