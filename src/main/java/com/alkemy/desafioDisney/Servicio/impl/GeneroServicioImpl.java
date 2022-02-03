package com.alkemy.desafioDisney.Servicio.impl;

import com.alkemy.desafioDisney.Dto.GeneroDTO;
import com.alkemy.desafioDisney.Entidad.Genero;
import com.alkemy.desafioDisney.Enum.Errors;
import com.alkemy.desafioDisney.Exception.ParamNotFound;
import com.alkemy.desafioDisney.Mapper.GeneroMapper;
import com.alkemy.desafioDisney.Repositorio.GeneroRepositorio;
import com.alkemy.desafioDisney.Servicio.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public GeneroDTO modificarGenero(GeneroDTO dto) throws ParamNotFound {
        Genero genero = this.buscarPorId(dto.getId());
        generoRepositorio.save(genero);
        return generoMapper.generoEntidad2DTO(genero);
    }

    public Genero buscarPorId(String id) throws ParamNotFound {
        Optional<Genero> busqueda = generoRepositorio.findById(id);
        if (!busqueda.isPresent()){
            Errors errors = Errors.GENERO_NOT_FOUND;
            throw new ParamNotFound(errors.getMessege());
        }
        return busqueda.get();
    }
}
