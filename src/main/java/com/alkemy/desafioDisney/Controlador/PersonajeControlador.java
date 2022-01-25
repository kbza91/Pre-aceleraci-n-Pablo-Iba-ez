package com.alkemy.desafioDisney.Controlador;

import com.alkemy.desafioDisney.Dto.PersonajeBaseDTO;
import com.alkemy.desafioDisney.Dto.PersonajeDTO;
import com.alkemy.desafioDisney.Exception.ParamNotFound;
import com.alkemy.desafioDisney.Servicio.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("characters")
public class PersonajeControlador {

    @Autowired
    private PersonajeServicio personajeServicio;

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> detallePersonaje(@PathVariable String id) throws ParseException, ParamNotFound{
        PersonajeDTO dto = personajeServicio.obtenerPersonaje(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> guardar (@RequestBody PersonajeDTO personaje) throws ParseException {
        PersonajeDTO personajeGuardado = personajeServicio.guardarPersonaje(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar (@PathVariable String id){
        personajeServicio.borrar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> modificar (@PathVariable String id, @RequestBody PersonajeDTO personajeModificado) throws ParamNotFound, ParseException {
        PersonajeDTO personajeEditado = personajeServicio.modificarPersonaje(id, personajeModificado);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personajeEditado);
    }

    @GetMapping
    public ResponseEntity<List<PersonajeBaseDTO>> personajesConFiltro(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) List<String> peliculas
    ) {
        List<PersonajeBaseDTO> listaFinal = personajeServicio.buscarConFlitro(nombre, edad, peliculas);
        return ResponseEntity.status(HttpStatus.OK).body(listaFinal);
    }
}
