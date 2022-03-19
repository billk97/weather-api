package com.example.repository;

import com.example.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public User findUserByName(String name) {return find("name",name).firstResult();}
}
