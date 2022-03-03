package com.example.adapters.users.ports;


import com.example.adapters.users.dtos.UserDTO;
import io.quarkus.arc.Priority;
import java.util.Optional;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@Alternative
@Priority(1)
@ApplicationScoped
@RestClient
public class UserStub implements UserPort{


    @Override
    public Optional<UserDTO> findUserById(String id) {
        return Optional.of(new UserDTO(1L, "bill", 1L, Set.of(1L, 2L)));
    }
}