package com.alkemy.desafioDisney.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeFiltroDTO {

    private String nombre;
    private Integer edad;
    private List<String> peliculas;

}
