package com.example.adapters.users.ports;

import com.example.adapters.users.dtos.UserDTO;
import java.util.Optional;

public interface UsersPort {

    Optional<UserDTO> findUserById(String id);
}
