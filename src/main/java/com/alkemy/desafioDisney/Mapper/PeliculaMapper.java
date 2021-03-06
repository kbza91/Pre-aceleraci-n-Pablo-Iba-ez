package com.alkemy.desafioDisney.Mapper;

import com.alkemy.desafioDisney.Dto.PelioSerieBaseDTO;
import com.alkemy.desafioDisney.Dto.PelioSerieDTO;
import com.alkemy.desafioDisney.Dto.PersonajeDTO;
import com.alkemy.desafioDisney.Entidad.PelioSerie;
import com.alkemy.desafioDisney.Entidad.Personaje;
import com.alkemy.desafioDisney.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaMapper {

    @Autowired
    private PersonajeMapper personajeMapper;

    public PelioSerie peliDTO2Entidad (PelioSerieDTO dto, boolean cargarPersonajes) throws ParseException {

        PelioSerie pelioSerie = new PelioSerie();
        pelioSerie.setTitulo(dto.getTitulo());
        pelioSerie.setCalificacion(dto.getCalificacion());
        pelioSerie.setImagen(dto.getImagen());
        pelioSerie.setFechaCreacion(Utils.stringToDate(dto.getFechaCreacion()));
        pelioSerie.setGeneroId(dto.getGeneroId());

        if (cargarPersonajes){
            List<Personaje> personajes = new ArrayList<>();
            for (PersonajeDTO personajeDTO : dto.getPersonajes()) {
                personajes.add(personajeMapper.personajeDTO2Entidad(personajeDTO));
            }
            pelioSerie.setPersonajes(personajes);
        }
        return pelioSerie;
    }

    public PelioSerieDTO peliEntidad2DTO (PelioSerie entidad, boolean cargarPersonajes) throws ParseException {

        PelioSerieDTO dto = new PelioSerieDTO();
        dto.setId(entidad.getId());
        dto.setTitulo(entidad.getTitulo());
        dto.setCalificacion(entidad.getCalificacion());
        dto.setImagen(entidad.getImagen());
        dto.setGeneroId(entidad.getGeneroId());

        dto.setFechaCreacion(Utils.dateToString(entidad.getFechaCreacion()));

        if (cargarPersonajes){
            List<PersonajeDTO> personajesDTO = new ArrayList<>();
            for (Personaje personaje : entidad.getPersonajes()) {
                personajesDTO.add(personajeMapper.personajeEntidad2DTO(personaje, false));
            }
            dto.setPersonajes(personajesDTO);
        }
        return dto;
    }

    public List<PelioSerie> peliDTOList2EntidadList (List<PelioSerieDTO> listdto, boolean cargarPersonaje) throws ParseException {
        List<PelioSerie> listaEntidad = new ArrayList<>();
        for (PelioSerieDTO dtos :
                listdto) {
            listaEntidad.add(peliDTO2Entidad(dtos, cargarPersonaje));
        }
        return listaEntidad;
    }

    public List<PelioSerieBaseDTO> peliEntidadList2BaseDTOList (List<PelioSerie> listEntidad){
        List<PelioSerieBaseDTO> listDTOBase = new ArrayList<>();
        for (PelioSerie pelis :
                listEntidad) {
            listDTOBase.add(peliEntidad2DTOBase(pelis));
        }
        return listDTOBase;
    }

    public PelioSerieBaseDTO peliEntidad2DTOBase (PelioSerie entidad){

        PelioSerieBaseDTO dto = new PelioSerieBaseDTO();
        dto.setId(entidad.getId());
        dto.setTitulo(entidad.getTitulo());
        dto.setImagen(entidad.getImagen());
        dto.setFechaCreacion(Utils.dateToString(entidad.getFechaCreacion()));

        return dto;
    }
}
