package inno.tech.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tpp_ref_product_register_type")
@Getter
public class RefProductRegisterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalId;
    private String value;
    private String registerTypeName;
//    private String productClassCode;
    private LocalDateTime registerTypeStartDate;
    private LocalDateTime registerTypeEndDate;
    private String accountType;

    @ManyToOne
    @JoinColumn(name = "productClassCode")
    private List<RefProductRegisterType> registerType;
}
