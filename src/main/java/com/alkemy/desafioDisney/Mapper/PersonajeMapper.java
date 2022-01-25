package com.alkemy.desafioDisney.Mapper;

import com.alkemy.desafioDisney.Dto.PelioSerieDTO;
import com.alkemy.desafioDisney.Dto.PersonajeBaseDTO;
import com.alkemy.desafioDisney.Dto.PersonajeDTO;
import com.alkemy.desafioDisney.Entidad.PelioSerie;
import com.alkemy.desafioDisney.Entidad.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

    public Personaje personajeDTO2Entidad (PersonajeDTO dto){
        Personaje personaje = new Personaje();
        personaje.setEdad(dto.getEdad());
        personaje.setHistoria(dto.getHistoria());
        personaje.setImagen(dto.getImagen());
        personaje.setPeso(dto.getPeso());
        personaje.setNombre(dto.getNombre());
        return personaje;
    }

    public PersonajeDTO personajeEntidad2DTO (Personaje personaje, boolean cargarPelicula) throws ParseException {
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(personaje.getId());
        dto.setHistoria(personaje.getHistoria());
        dto.setEdad(personaje.getEdad());
        dto.setImagen(personaje.getImagen());
        dto.setPeso(personaje.getPeso());
        dto.setNombre(personaje.getNombre());

        if (cargarPelicula){
            List<PelioSerieDTO> dtos = new ArrayList<>();
            for (PelioSerie peliOseries :
                    personaje.getPelioSeries()) {
                dtos.add(peliculaMapper.peliEntidad2DTO(peliOseries, false));
            }
            dto.setPeliculas(dtos);
        }

        return dto;
    }

    public Personaje personajeBaseDTO2Entidad (PersonajeBaseDTO dto){
        Personaje personaje = new Personaje();
        personaje.setImagen(dto.getImagen());
        personaje.setNombre(dto.getNombre());
        return personaje;
    }

    public PersonajeBaseDTO personajeEntidad2DTObase (Personaje personaje){
        PersonajeBaseDTO dto = new PersonajeBaseDTO();
        dto.setId(personaje.getId());
        dto.setImagen(personaje.getImagen());
        dto.setNombre(personaje.getNombre());
        return dto;
    }

    public List<PersonajeBaseDTO> listaPersonajeEntidad2DTObase (List<Personaje> entidad){
        List<PersonajeBaseDTO> dtos = new ArrayList<>();
        for (Personaje personaje : entidad){
            dtos.add(personajeEntidad2DTObase(personaje));
        }
        return dtos;
    }
}
