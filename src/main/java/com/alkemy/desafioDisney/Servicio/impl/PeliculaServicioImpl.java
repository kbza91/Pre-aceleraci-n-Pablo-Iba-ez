package com.alkemy.desafioDisney.Servicio.impl;

import com.alkemy.desafioDisney.Dto.*;
import com.alkemy.desafioDisney.Entidad.PelioSerie;
import com.alkemy.desafioDisney.Entidad.Personaje;
import com.alkemy.desafioDisney.Enum.Errors;
import com.alkemy.desafioDisney.Exception.ParamNotFound;
import com.alkemy.desafioDisney.Mapper.GeneroMapper;
import com.alkemy.desafioDisney.Mapper.PeliculaMapper;
import com.alkemy.desafioDisney.Mapper.PersonajeMapper;
import com.alkemy.desafioDisney.Repositorio.PeliculaRepositorio;
import com.alkemy.desafioDisney.Repositorio.Specification.PeliculaSpecification;
import com.alkemy.desafioDisney.Servicio.PeliculaServicio;
import com.alkemy.desafioDisney.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServicioImpl implements PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Autowired
    private PeliculaSpecification peliculaSpecification;
    @Autowired
    private PeliculaMapper peliculaMapper;
    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private GeneroMapper generoMapper;
    @Autowired
    private GeneroServicioImpl generoServicio;

    public List<PelioSerieBaseDTO> buscarPorFiltro(String nombre, String idGenero, String order, String fechaCreacion) {
        PeliculaFiltroDTO filtroDTO = new PeliculaFiltroDTO(nombre, idGenero, order, fechaCreacion);
        List<PelioSerie> entidades = peliculaRepositorio.findAll(peliculaSpecification.getByFilters(filtroDTO));
        return peliculaMapper.peliEntidadList2BaseDTOList(entidades);
    }

    public PelioSerieDTO guardarPelicula(PelioSerieDTO dto) throws ParseException {
        PelioSerie entidad = peliculaMapper.peliDTO2Entidad(dto, true);
        PelioSerie entidadGuardada = peliculaRepositorio.save(entidad);
        return peliculaMapper.peliEntidad2DTO(entidadGuardada, true);
    }

    public void borrar(String id) throws ParamNotFound{
        PelioSerie peli = this.buscarPorId(id);
        if (peli != null){
            peliculaRepositorio.deleteById(id);
        }
    }

    public PelioSerieDTO modificarPelicula(String id, PelioSerieDTO dtoNuevo, boolean cargarPersonaje) throws ParseException, ParamNotFound {
        PelioSerie busqueda = this.buscarPorId(id);

        busqueda.setTitulo(dtoNuevo.getTitulo());
        busqueda.setCalificacion(dtoNuevo.getCalificacion());
        busqueda.setImagen(dtoNuevo.getImagen());
        busqueda.setGeneroId(dtoNuevo.getGeneroId());
        busqueda.setFechaCreacion(Utils.stringToDate(dtoNuevo.getFechaCreacion()));

        if (cargarPersonaje){
            List<Personaje> personajes = new ArrayList<>();
            for (PersonajeDTO personajeDTO : dtoNuevo.getPersonajes()) {
                personajes.add(personajeMapper.personajeDTO2Entidad(personajeDTO));
            }
            busqueda.setPersonajes(personajes);
        }
        PelioSerie peliculaModificada = peliculaRepositorio.save(busqueda);

        return peliculaMapper.peliEntidad2DTO(peliculaModificada, true);
    }

    public PelioSerie buscarPorId(String id) throws ParamNotFound {
        Optional<PelioSerie> result = peliculaRepositorio.findById(id);

        if (!result.isPresent()) {
            Errors errors = Errors.PELICULA_NOT_FOUND;
            throw new ParamNotFound(errors.getMessege());
        }
        return result.get();
    }

    public PelioSerieDTO obtenerDetallePeli(String id) throws ParamNotFound, ParseException {
        PelioSerie busqueda = this.buscarPorId(id);
        return peliculaMapper.peliEntidad2DTO(busqueda, true);
    }


}
