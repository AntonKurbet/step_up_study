package inno.tech.repositories;

import inno.tech.entity.RefProductRegisterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefProductRegisterTypeRepository extends JpaRepository<RefProductRegisterType, Integer> {
    Optional<RefProductRegisterType> findByValue(String value);

    List<RefProductRegisterType> findAllByAccountType(String type);
}
