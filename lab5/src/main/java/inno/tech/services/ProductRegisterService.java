package inno.tech.services;

import inno.tech.dto.AccountCreateRequestDto;
import inno.tech.dto.AccountCreateResponseDataDto;
import inno.tech.dto.AccountCreateResponseDto;
import inno.tech.dto.RegisterState;
import inno.tech.entity.ProductRegister;
import inno.tech.repositories.AccountPoolRepository;
import inno.tech.repositories.ProductRegisterRepository;
import inno.tech.repositories.RefProductRegisterTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@AllArgsConstructor
public class ProductRegisterService {

    private final ProductRegisterRepository productRegisterRepository;
    private final RefProductRegisterTypeRepository refProductRegisterTypeRepository;
    private final AccountPoolRepository accountPoolRepository;

    public AccountCreateResponseDto create(AccountCreateRequestDto request) {
        if (productRegisterRepository.findByIdAndType(request.getInstanceId(), request.getRegistryTypeCode()).isPresent()) {
            return null;
        }
        if (refProductRegisterTypeRepository.findByValue(request.getRegistryTypeCode()).isEmpty()) {
            return null;
        }
        var list = accountPoolRepository.findByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(
                request.getBranchCode(), request.getCurrencyCode(), request.getMdmCode(), request.getPriorityCode(),
                request.getRegistryTypeCode()
        );
        if (list.isPresent()) {
            var account = CollectionUtils.firstElement(list.get().getAccounts());
            if (account == null) return null;

            productRegisterRepository.save(
                    new ProductRegister(
                            null,
                            request.getInstanceId(),
                            request.getRegistryTypeCode(),
                            account.getId(),
                            request.getCurrencyCode(),
                            String.valueOf(RegisterState.OPEN.ordinal()),
                            account.getAccountNumber()
                    )
            );

            return new AccountCreateResponseDto(
                    new AccountCreateResponseDataDto(
                            account.getAccountNumber()
                    )
            );
        }
        return null;
    }
}
