package inno.tech.repositories;

import inno.tech.entity.RefProductRegisterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefProductRegisterTypeRepository extends JpaRepository<RefProductRegisterType, Long> {
    Optional<RefProductRegisterType> findByValue(String value);
}
