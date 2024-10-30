package mariapiabaldoin.Giorno_3.repositories;

import mariapiabaldoin.Giorno_3.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AutoriRepository extends JpaRepository<Autore, Long> {
    Optional<Autore> findByEmail(String email);
}