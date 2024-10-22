package eci.cvds.armario.repository;

import eci.cvds.armario.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID>{
    Session findByToken(UUID token);
}
