package com.alkemy.desafioDisney.Entidad;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pelicula_o_serie")
@Data
@SQLDelete(sql = "UPDATE pelicula_o_serie SET borrado = true WHERE id = ?")
@Where(clause = "borrado = false")
public class PelioSerie implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String imagen;
    private String titulo;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    private Integer calificacion; // Calificar del 1 al 5

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "personaje_peli",
                joinColumns = @JoinColumn(name = "peli_id"),
                inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<Personaje> personajes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private Genero genero;

    @Column(name = "genero_id")
    private String generoId;

    private Boolean borrado = Boolean.FALSE;
}
