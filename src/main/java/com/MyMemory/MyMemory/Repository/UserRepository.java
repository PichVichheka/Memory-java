package com.MyMemory.MyMemory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MyMemory.MyMemory.Enitity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
