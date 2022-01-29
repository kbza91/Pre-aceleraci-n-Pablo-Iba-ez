package com.alkemy.desafioDisney.Controlador;

import com.alkemy.desafioDisney.Dto.PelioSerieBaseDTO;
import com.alkemy.desafioDisney.Dto.PelioSerieDTO;
import com.alkemy.desafioDisney.Exception.ParamNotFound;
import com.alkemy.desafioDisney.Servicio.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("movies")
public class PeliculaControlador {

    @Autowired
    private PeliculaServicio peliculaServicio;

    @GetMapping
    public ResponseEntity<List<PelioSerieBaseDTO>> obtenerConFiltro(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String idGenero,
            @RequestParam(required = false, defaultValue = "ASC") String order,
            @RequestParam(required = false) String fechaCreacion
    ){
        List<PelioSerieBaseDTO> pelis = peliculaServicio.buscarPorFiltro(nombre, idGenero, order, fechaCreacion);
        return ResponseEntity.status(HttpStatus.OK).body(pelis);
    }

    @PostMapping
    public ResponseEntity<PelioSerieDTO> guardarPelicula(@RequestBody PelioSerieDTO dto) throws ParseException {
        PelioSerieDTO peliculaGuardada = peliculaServicio.guardarPelicula(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPelicula (@PathVariable String id){
        peliculaServicio.borrar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PelioSerieDTO> modificar(@PathVariable String id, @RequestBody PelioSerieDTO dtoNuevo) throws ParseException, ParamNotFound {
        PelioSerieDTO peliculaEditada = peliculaServicio.modificarPelicula(id, dtoNuevo, true);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliculaEditada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PelioSerieDTO> mostrarPeliDetalle(@PathVariable String id) throws ParseException, ParamNotFound {
        PelioSerieDTO busqueda = peliculaServicio.obtenerDetallePeli(id);
        return ResponseEntity.status(HttpStatus.OK).body(busqueda);
    }
}
