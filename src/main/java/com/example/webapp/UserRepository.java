package com.example.webapp;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);
    boolean existsUserByUsername(String username);
}
