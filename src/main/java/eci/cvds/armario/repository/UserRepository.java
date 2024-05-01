package eci.cvds.armario.repository;

import eci.cvds.armario.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    //@Query("SELECT e FROM Users e WHERE e.username = ?1")
    Optional<User> findByUsername(String username);
}
