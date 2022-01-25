package com.alkemy.desafioDisney.Dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PersonajeBaseDTO implements Serializable {
    String id;
    String nombre;
    String imagen;
}
