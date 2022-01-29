package com.alkemy.desafioDisney.Servicio;

import com.alkemy.desafioDisney.Dto.PelioSerieBaseDTO;
import com.alkemy.desafioDisney.Dto.PelioSerieDTO;
import com.alkemy.desafioDisney.Entidad.PelioSerie;
import com.alkemy.desafioDisney.Exception.ParamNotFound;

import java.text.ParseException;
import java.util.List;

public interface PeliculaServicio {

    List<PelioSerieBaseDTO> buscarPorFiltro (String nombre, String idGenero, String order, String fechaCreacion);

    PelioSerieDTO guardarPelicula (PelioSerieDTO dto) throws ParseException;

    void borrar (String id);

    PelioSerieDTO modificarPelicula(String id, PelioSerieDTO dtoNuevo, boolean b) throws ParseException, ParamNotFound;

    PelioSerie buscarPorId (String id) throws ParamNotFound;

    PelioSerieDTO obtenerDetallePeli (String id) throws ParamNotFound, ParseException;
}
