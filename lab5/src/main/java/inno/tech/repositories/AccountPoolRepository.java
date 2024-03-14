package inno.tech.repositories;

import inno.tech.entity.AccountPool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountPoolRepository extends JpaRepository<AccountPool, Integer> {
    Optional<AccountPool> findByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(String branchCode, String currencyCode, String mdmCode, String priorityCode, String registryTypeCode);
}
