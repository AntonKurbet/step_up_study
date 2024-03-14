package inno.tech.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "tpp_ref_product_register_type")
@Getter
@NoArgsConstructor
public class RefProductRegisterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internalId;
    private String value;
    private String registerTypeName;
    //    private String productClassCode;
    private LocalDateTime registerTypeStartDate;
    private LocalDateTime registerTypeEndDate;
    private String accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productClassCode", referencedColumnName = "value")
    private RefProductClass refProductClass;
}
