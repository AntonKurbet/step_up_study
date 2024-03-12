package inno.tech.repositories;

import inno.tech.entity.ProductRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRegisterRepository extends JpaRepository<ProductRegister, Long> {

    Optional<ProductRegister> findByIdAndType(Long id, String type);
}

