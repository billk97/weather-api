package com.example.adapters.users;

import com.example.adapters.users.dtos.UserDTO;
import com.example.adapters.users.ports.UsersPort;
import java.util.Optional;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserAdapter implements UsersPort {

    @Override
    public Optional<UserDTO> findUserById(String id) {
        return Optional.of(new UserDTO(1L, "bill", 1L, Set.of(1L, 2L)));
    }
}
