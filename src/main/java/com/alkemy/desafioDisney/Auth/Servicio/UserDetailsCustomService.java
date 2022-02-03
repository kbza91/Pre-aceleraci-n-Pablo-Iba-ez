package com.alkemy.desafioDisney.Auth.Servicio;

import com.alkemy.desafioDisney.Auth.DTO.UserDTO;
import com.alkemy.desafioDisney.Auth.Entidad.UserEntity;
import com.alkemy.desafioDisney.Auth.Repositorio.UserRepository;
import com.alkemy.desafioDisney.Enum.Errors;
import com.alkemy.desafioDisney.Servicio.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null){
            throw new UsernameNotFoundException(Errors.USER_NOT_FOUND.getMessege());
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(@Valid UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity = userRepository.save(userEntity);
        if (userEntity != null){
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }
}

