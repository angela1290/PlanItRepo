package com.example.webapp.todo;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TodoRepository extends JpaRepository<Todo, Long>{
    List<Todo> findByUserName(String user);

    Optional<Todo> findById(long id);
}
