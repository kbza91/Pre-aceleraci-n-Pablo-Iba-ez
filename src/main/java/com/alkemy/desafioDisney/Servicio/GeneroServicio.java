package com.alkemy.desafioDisney.Servicio;

import com.alkemy.desafioDisney.Dto.GeneroDTO;
import com.alkemy.desafioDisney.Entidad.Genero;
import com.alkemy.desafioDisney.Exception.ParamNotFound;

public interface GeneroServicio {

    GeneroDTO guardarGenero(GeneroDTO dto);

    void borrar (String id);

    GeneroDTO modificarGenero(GeneroDTO dto) throws ParamNotFound;

    Genero buscarPorId (String id) throws ParamNotFound;

}
