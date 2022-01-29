package com.alkemy.desafioDisney.Repositorio;

import com.alkemy.desafioDisney.Entidad.PelioSerie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepositorio extends JpaRepository<PelioSerie, String>, JpaSpecificationExecutor<PelioSerie> {
    List<PelioSerie> findAll (Specification<PelioSerie> spec);
}
