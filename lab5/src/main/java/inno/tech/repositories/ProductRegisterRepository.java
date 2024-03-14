package inno.tech.repositories;

import inno.tech.entity.ProductRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRegisterRepository extends JpaRepository<ProductRegister, Integer> {

    Optional<ProductRegister> findByIdAndType(Integer id, String type);
}

