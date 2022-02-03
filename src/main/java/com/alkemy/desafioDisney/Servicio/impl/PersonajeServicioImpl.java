package com.alkemy.desafioDisney.Servicio.impl;

import com.alkemy.desafioDisney.Dto.PersonajeBaseDTO;
import com.alkemy.desafioDisney.Dto.PersonajeDTO;
import com.alkemy.desafioDisney.Dto.PersonajeFiltroDTO;
import com.alkemy.desafioDisney.Entidad.Personaje;
import com.alkemy.desafioDisney.Enum.Errors;
import com.alkemy.desafioDisney.Exception.ParamNotFound;
import com.alkemy.desafioDisney.Mapper.PersonajeMapper;
import com.alkemy.desafioDisney.Repositorio.PersonajeRepositorio;
import com.alkemy.desafioDisney.Repositorio.Specification.PersonajeSpecification;
import com.alkemy.desafioDisney.Servicio.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServicioImpl implements PersonajeServicio {

    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepositorio personajeRepositorio;
    @Autowired
    private PersonajeSpecification personajeSpecification;

    public PersonajeDTO guardarPersonaje(PersonajeDTO dto) throws ParseException {
        Personaje personaje = personajeMapper.personajeDTO2Entidad(dto);
        Personaje personajeGuardado = personajeRepositorio.save(personaje);
        return personajeMapper.personajeEntidad2DTO(personajeGuardado, false);
    }

    public List<PersonajeBaseDTO> buscarTodos(){
        List<Personaje> buscarTodosPersonajes = personajeRepositorio.findAll();
        return personajeMapper.listaPersonajeEntidad2DTObase(buscarTodosPersonajes);
    }

    public void borrar(String id) throws ParamNotFound{
        Personaje personaje = this.buscarPorId(id);
        if (personaje != null ) {
            personajeRepositorio.deleteById(id);
        }
    }

    public PersonajeDTO modificarPersonaje(String id, PersonajeDTO dto) throws ParamNotFound, ParseException {
        Personaje personaje = this.buscarPorId(id);

        personaje.setNombre(dto.getNombre());
        personaje.setImagen(dto.getImagen());
        personaje.setHistoria(dto.getHistoria());
        personaje.setPeso(dto.getPeso());
        personaje.setEdad(dto.getEdad());

        Personaje personajeEditado = personajeRepositorio.save(personaje);

        return personajeMapper.personajeEntidad2DTO(personajeEditado, false);
    }

    public Personaje buscarPorId(String id) throws ParamNotFound {
        Optional<Personaje> personaje = personajeRepositorio.findById(id);
        if (!personaje.isPresent()){
            throw new ParamNotFound(Errors.PERSONAJE_NOT_FOUND.getMessege());
        }
        return personaje.get();
    }

    public PersonajeDTO obtenerPersonaje(String id) throws ParseException, ParamNotFound {
        Personaje personaje = this.buscarPorId(id);
        return personajeMapper.personajeEntidad2DTO(personaje, true);
    }

    public List<PersonajeBaseDTO> buscarConFlitro(String nombre, String edad, List<String> peliculas) {
        PersonajeFiltroDTO filtroDTO = new PersonajeFiltroDTO(nombre, edad, peliculas);
        List<Personaje> entidades = personajeRepositorio.findAll(personajeSpecification.buscarPorFiltros(filtroDTO));
        return personajeMapper.listaPersonajeEntidad2DTObase(entidades);
    }

}
