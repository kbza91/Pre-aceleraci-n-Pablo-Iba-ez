package com.alkemy.desafioDisney.Repositorio;

import com.alkemy.desafioDisney.Entidad.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, String> {

}
