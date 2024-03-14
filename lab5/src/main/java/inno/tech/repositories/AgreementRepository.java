package inno.tech.repositories;

import inno.tech.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
    Optional<Agreement> findByNumber(String number);
}
