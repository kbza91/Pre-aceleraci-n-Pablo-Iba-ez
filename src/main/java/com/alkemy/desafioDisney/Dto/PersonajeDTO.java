package com.alkemy.desafioDisney.Dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PersonajeDTO implements Serializable {
    String id;
    String imagen;
    String nombre;
    Integer edad;
    Double peso;
    String historia;
    List<PelioSerieDTO> peliculas;
}
