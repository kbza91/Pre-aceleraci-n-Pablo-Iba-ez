package com.alkemy.desafioDisney.Repositorio.Specification;

import com.alkemy.desafioDisney.Dto.PersonajeFiltroDTO;
import com.alkemy.desafioDisney.Entidad.PelioSerie;
import com.alkemy.desafioDisney.Entidad.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeSpecification {

    public Specification<Personaje> buscarPorFiltros (PersonajeFiltroDTO filtroDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(filtroDTO.getNombre())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtroDTO.getNombre().toLowerCase() + "%")
                );
            }
            if (StringUtils.hasLength(String.valueOf(filtroDTO.getEdad()))){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("edad")),
                                "%" + filtroDTO.getEdad() + "%")
                );
            }
            if (!CollectionUtils.isEmpty(filtroDTO.getPeliculas())){
                Join<PelioSerie, Personaje> toBeJoin = root.join("peliculas", JoinType.INNER);
                Expression<String> peliculaId = toBeJoin.get("id");

                predicates.add((peliculaId.in(filtroDTO.getPeliculas())));
            }
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
