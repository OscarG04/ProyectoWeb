
package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "grooming")
public class Grooming implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grooming")
    private Long idGrooming;
    private LocalDateTime fechaHora;
    private String nombreCliente;
    private String tipoCorte;
    private String telefonoCliente;
    private String comentario;
    private String rutaImagen;
    private boolean activo;
}
