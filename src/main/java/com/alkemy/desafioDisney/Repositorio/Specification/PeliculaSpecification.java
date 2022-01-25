package com.alkemy.desafioDisney.Repositorio.Specification;

import com.alkemy.desafioDisney.Dto.PeliculaFiltroDTO;
import com.alkemy.desafioDisney.Entidad.PelioSerie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class PeliculaSpecification {

    public Specification<PelioSerie> getByFilters (PeliculaFiltroDTO filtroDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtroDTO.getNombre())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtroDTO.getNombre().toLowerCase() + "%")

                );
            }
            if (StringUtils.hasLength(filtroDTO.getGenero())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("idGenero")),
                                "%" + filtroDTO.getGenero().toLowerCase() + "%"
                        )
                );
            }
            if (StringUtils.hasLength(filtroDTO.getFechaCreacion())){

                SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaCreacion = null;
                try {
                    fechaCreacion = fechaFormat.parse(filtroDTO.getFechaCreacion());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                predicates.add(
                        criteriaBuilder.equal(root.<Date>get("fechaCreacion"), fechaCreacion)
                );
            }
            String orderByField = "fechaCreacion";
            query.orderBy(
                    filtroDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField))
                            :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
