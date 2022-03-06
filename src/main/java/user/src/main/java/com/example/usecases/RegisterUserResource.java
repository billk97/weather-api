package com.example.usecases;

import com.example.domain.User;
import com.example.dtos.in.RegisterUserDTO;
import com.example.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class RegisterUserResource {

    @Inject
    UserRepository userRepository;

    @Transactional
    public long command(RegisterUserDTO dto) {
        if(dto.name() == null) {
            throw new IllegalArgumentException("User name can not be empty");
        }
        User user = new User(dto);
        userRepository.persist(user);
        return user.getId();
    }
}
