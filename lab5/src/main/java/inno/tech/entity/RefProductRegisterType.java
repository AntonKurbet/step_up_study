package inno.tech.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity(name = "tpp_ref_product_register_type")
public class RefProductRegisterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalId;
    private String value;
    private String registerTypeName;
    private String productClassCode;
    private LocalDateTime registerTypeStartDate;
    private LocalDateTime registerTypeEndDate;
    private String accountType;
}
