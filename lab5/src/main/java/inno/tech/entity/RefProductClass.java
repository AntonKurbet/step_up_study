package inno.tech.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Entity(name = "tpp_ref_product_class")
@Getter
public class RefProductClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalId;
//    private String value;
    private String gbiCode;
    private String gbiName;
    private String productRowCode;
    private String productRowName;
    private String subclassCode;
    private String subclassName;

    @OneToMany(mappedBy = "value")
    private List<RefProductRegisterType> registerTypes;

}
