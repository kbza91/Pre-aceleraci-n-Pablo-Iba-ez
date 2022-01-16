package com.alkemy.desafioDisney.Entidad;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personaje")
@Data
public class Personaje implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;

    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<PelioSerie> pelioSeries = new ArrayList<>();
}