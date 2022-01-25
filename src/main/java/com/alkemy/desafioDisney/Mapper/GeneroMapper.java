package com.alkemy.desafioDisney.Mapper;

import com.alkemy.desafioDisney.Dto.GeneroDTO;
import com.alkemy.desafioDisney.Entidad.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    public Genero generoDTO2Entidad (GeneroDTO dto){
        Genero genero = new Genero();
        genero.setImagen(dto.getImagen());
        genero.setNombre(dto.getNombre());
        return genero;
    }

    public GeneroDTO generoEntidad2DTO (Genero genero){
        GeneroDTO dto = new GeneroDTO();
        dto.setId(genero.getId());
        dto.setNombre(genero.getNombre());
        dto.setImagen(genero.getImagen());
        return dto;
    }
}
