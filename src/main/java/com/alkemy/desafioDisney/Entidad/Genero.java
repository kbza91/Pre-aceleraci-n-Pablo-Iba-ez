package com.alkemy.desafioDisney.Entidad;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genero")
@Data
@SQLDelete(sql = "UPDATE genero SET borrado = true WHERE id = ?")
@Where(clause = "borrado = false")
public class Genero implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    private String imagen;

   @OneToMany(mappedBy = "genero")
    private List<PelioSerie> pelioSeries = new ArrayList<>();

   private Boolean borrado = Boolean.FALSE;
}
