package com.alkemy.desafioDisney.Servicio;

import com.alkemy.desafioDisney.Dto.PersonajeBaseDTO;
import com.alkemy.desafioDisney.Dto.PersonajeDTO;
import com.alkemy.desafioDisney.Entidad.Personaje;
import com.alkemy.desafioDisney.Exception.ParamNotFound;

import java.text.ParseException;
import java.util.List;

public interface PersonajeServicio {

    PersonajeDTO guardarPersonaje(PersonajeDTO dto) throws ParseException;

    List<PersonajeBaseDTO> buscarTodos();

    void borrar (String id);

    PersonajeDTO modificarPersonaje(String id, PersonajeDTO dto) throws ParamNotFound, ParseException;

    Personaje buscarPorId (String id) throws ParamNotFound;

    PersonajeDTO obtenerPersonaje (String id) throws ParseException, ParamNotFound;

    List<PersonajeBaseDTO> buscarConFlitro(String nombre, String edad, List<String> peliculas);

}
