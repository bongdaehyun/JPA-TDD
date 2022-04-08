package com.example.junitstudy.api.domain.repository;

import com.example.junitstudy.api.domain.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<? extends User> findByName(String name);

    @Override
    List<User> findAll();
}
