package com.alkemy.desafioDisney.Controlador;

import com.alkemy.desafioDisney.Dto.GeneroDTO;
import com.alkemy.desafioDisney.Servicio.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("generos")
public class GeneroControlador {

    @Autowired
    private GeneroServicio generoServicio;

    @PostMapping
    public ResponseEntity<GeneroDTO> guardar (@RequestBody GeneroDTO genero){
        GeneroDTO generoGuardado = generoServicio.guardarGenero(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar (@PathVariable String id){
        generoServicio.borrar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
