package inno.tech.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Entity(name = "account_pool")
@Getter
public class AccountPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchCode;
    private String currencyCode;
    private String mdmCode;
    private String priorityCode;
    private String registryTypeCode;

    @OneToMany(mappedBy = "accountPool")
    private List<Account> accounts;
}
