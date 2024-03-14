package inno.tech.repositories;

import inno.tech.entity.RefProductClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefProductClassRepository extends JpaRepository<RefProductClass,Integer> {
    Optional<RefProductClass> findByValue(String value);
}
