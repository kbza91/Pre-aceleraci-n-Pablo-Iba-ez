package com.alkemy.desafioDisney.Servicio;

import com.alkemy.desafioDisney.Dto.GeneroDTO;

public interface GeneroServicio {

    GeneroDTO guardarGenero(GeneroDTO dto);

    void borrar (String id);

}
