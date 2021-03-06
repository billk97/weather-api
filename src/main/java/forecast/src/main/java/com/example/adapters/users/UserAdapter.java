package com.example.adapters.users;

//@Alternative
//@Priority(1)
//@ApplicationScoped
//@RestClient
//public class UserStud implements UserAdapter {
//    @Override


import com.example.adapters.users.dtos.UserDTO;
import java.util.Optional;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserAdapter {
    public Optional<UserDTO> findUserById(String id) {
        return Optional.of(new UserDTO(1L, "bill", 1L, Set.of(1L, 2L)));
    }
}
