package com.alkemy.desafioDisney.Auth.Repositorio;

import com.alkemy.desafioDisney.Auth.Entidad.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
