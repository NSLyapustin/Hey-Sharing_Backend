package ru.itis.sharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.sharing.model.ApplicationUser;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findApplicationUserByUsername(String username);
    List<ApplicationUser> findAll();
}
