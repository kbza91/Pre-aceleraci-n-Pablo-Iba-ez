package com.alkemy.desafioDisney.Entidad;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genero")
@Data
public class Genero implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    private String imagen;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "peli_genero",
                joinColumns = @JoinColumn(name = "genero_id"),
                inverseJoinColumns = @JoinColumn(name = "pelioSerie_id"))
    private List<PelioSerie> pelioSeries = new ArrayList<>();
}
