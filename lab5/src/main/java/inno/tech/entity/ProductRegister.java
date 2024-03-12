package inno.tech.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "tpp_product_register")
@AllArgsConstructor
@NoArgsConstructor
public class ProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String type;
    private Long account;
    private String currencyCode;
    private String state;
    private String accountNumber;
}
