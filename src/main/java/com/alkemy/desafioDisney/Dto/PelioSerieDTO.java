package com.alkemy.desafioDisney.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PelioSerieDTO implements Serializable {
    String id;
    String imagen;
    String titulo;
    String fechaCreacion;
    Integer calificacion;
    List<PersonajeDTO> personajes;
    String generoId;
}
