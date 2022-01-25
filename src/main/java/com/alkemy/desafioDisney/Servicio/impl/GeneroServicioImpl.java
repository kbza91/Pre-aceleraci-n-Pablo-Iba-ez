package com.alkemy.desafioDisney.Servicio.impl;

import com.alkemy.desafioDisney.Dto.GeneroDTO;
import com.alkemy.desafioDisney.Entidad.Genero;
import com.alkemy.desafioDisney.Mapper.GeneroMapper;
import com.alkemy.desafioDisney.Repositorio.GeneroRepositorio;
import com.alkemy.desafioDisney.Servicio.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServicioImpl implements GeneroServicio {

    @Autowired
    private GeneroMapper generoMapper;
    @Autowired
    private GeneroRepositorio generoRepositorio;

    public GeneroDTO guardarGenero(GeneroDTO dto){
        Genero genero = generoMapper.generoDTO2Entidad(dto);
        Genero generoguardado = generoRepositorio.save(genero);
        return generoMapper.generoEntidad2DTO(generoguardado);
    }

    public void borrar(String id) {
        generoRepositorio.deleteById(id);
    }
}
