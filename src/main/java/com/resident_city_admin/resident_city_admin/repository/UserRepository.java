package com.resident_city_admin.resident_city_admin.repository;

import com.resident_city_admin.resident_city_admin.domain.user.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(Long Id);
    Optional<User> findUserByEmail(Email email);
    Optional<List> findUserByCity(String city);

}
