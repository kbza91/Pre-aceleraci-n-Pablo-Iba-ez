package com.alkemy.desafioDisney.Servicio.impl;

import com.alkemy.desafioDisney.Dto.PeliculaFiltroDTO;
import com.alkemy.desafioDisney.Dto.PelioSerieBaseDTO;
import com.alkemy.desafioDisney.Dto.PelioSerieDTO;
import com.alkemy.desafioDisney.Dto.PersonajeDTO;
import com.alkemy.desafioDisney.Entidad.PelioSerie;
import com.alkemy.desafioDisney.Entidad.Personaje;
import com.alkemy.desafioDisney.Exception.ParamNotFound;
import com.alkemy.desafioDisney.Mapper.PeliculaMapper;
import com.alkemy.desafioDisney.Mapper.PersonajeMapper;
import com.alkemy.desafioDisney.Repositorio.PeliculaRepositorio;
import com.alkemy.desafioDisney.Repositorio.Specification.PeliculaSpecification;
import com.alkemy.desafioDisney.Servicio.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public void borrar(String id) {
        peliculaRepositorio.deleteById(id);
    }

    public PelioSerieDTO modificarPelicula(String id, PelioSerieDTO dtoNuevo, boolean cargarPersonaje) throws ParseException, ParamNotFound {
        PelioSerie busqueda = this.buscarPorId(id);

        busqueda.setTitulo(dtoNuevo.getTitulo());
        busqueda.setCalificacion(dtoNuevo.getCalificacion());
        busqueda.setImagen(dtoNuevo.getImagen());
        busqueda.setGeneroId(dtoNuevo.getGeneroId());

        SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fetch = fechaFormat.parse(dtoNuevo.getFechaCreacion());
        busqueda.setFechaCreacion(fetch);

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
            throw new ParamNotFound("Pelicula con id: " + id + " no fue encontrado");
        }
        return result.get();
    }


}
