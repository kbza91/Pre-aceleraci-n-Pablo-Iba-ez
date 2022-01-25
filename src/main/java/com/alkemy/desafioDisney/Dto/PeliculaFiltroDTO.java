package com.alkemy.desafioDisney.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaFiltroDTO {

    private String nombre;
    private String genero;
    private String orden;
    private String fechaCreacion;

    public boolean isASC (){return orden.compareToIgnoreCase("ASC") == 0;}

    public boolean isDESC (){return orden.compareToIgnoreCase("DESC") == 0;}
}
