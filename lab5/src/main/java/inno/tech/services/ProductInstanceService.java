package inno.tech.services;

import inno.tech.dto.InstanceArrangement;
import inno.tech.dto.InstanceCreateRequestDto;
import inno.tech.dto.InstanceCreateResponseDataDto;
import inno.tech.dto.InstanceCreateResponseDto;
import inno.tech.dto.RegisterState;
import inno.tech.entity.Agreement;
import inno.tech.entity.Product;
import inno.tech.entity.ProductRegister;
import inno.tech.exceptions.AgreementAlreadyPresentException;
import inno.tech.exceptions.ContractNumberExistException;
import inno.tech.exceptions.ProductCodeNotFountException;
import inno.tech.exceptions.ProductInstanceNotFoundException;
import inno.tech.repositories.AccountPoolRepository;
import inno.tech.repositories.AgreementRepository;
import inno.tech.repositories.ProductRegisterRepository;
import inno.tech.repositories.ProductRepository;
import inno.tech.repositories.RefProductClassRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductInstanceService {

    public static final String CLIENT_ACCOUNT_TYPE = "Клиентский";
    ProductRepository productRepository;
    RefProductClassRepository refProductClassRepository;
    ProductRegisterRepository productRegisterRepository;
    AccountPoolRepository accountPoolRepository;
    AgreementRepository agreementRepository;

    public InstanceCreateResponseDto create(InstanceCreateRequestDto request) throws ContractNumberExistException, ProductCodeNotFountException, ProductInstanceNotFoundException, AgreementAlreadyPresentException {
        var registerIdList = new ArrayList<Integer>();
        var agreementIdList = new ArrayList<Integer>();
        var instanceId = request.getInstanceId();
        if (instanceId == null) {
            var oldProduct = productRepository.findByNumber(request.getContractNumber());
            if (oldProduct.isPresent()) {
                throw new ContractNumberExistException(request.getContractNumber(), oldProduct.get().getNumber());
            }
            checkAgreements(request.getInstanceArrangement());
            var productClass = refProductClassRepository.findByValue(request.getProductCode());
            if (productClass.isPresent()) {
                var types = productClass.get().getRegisterTypes().stream()
                        .filter(t -> t.getAccountType().equals(CLIENT_ACCOUNT_TYPE)).toList();
                if (types.isEmpty()) {
                    throw new ProductCodeNotFountException(request.getProductCode());
                }
                var product = new Product(
                        null,
                        Integer.parseInt(request.getProductCode()),
                        Integer.parseInt(request.getMdmCode()),
                        request.getProductType(),
                        request.getContractNumber(),
                        request.getPriority(),
                        request.getContractDate().atStartOfDay(),
                        null,
                        null,
                        null,
                        request.getInterestRatePenalty(),
                        request.getMinimalBalance(),
                        request.getThresholdAmount(),
                        request.getAccountingDetails(),
                        request.getRateType(),
                        request.getTaxPercentageRate(),
                        null,
                        null
                );
                productRepository.save(product);
                instanceId = product.getId();
                var list = accountPoolRepository.findByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(
                        request.getBranchCode(), request.getIsoCurrencyCode(), request.getMdmCode(),
                        request.getPriority().toString(), product.getType());
                if (list.isPresent()) {

                    for (var account : list.get().getAccounts()) {
                        if (account == null) return null;
                        var register = new ProductRegister(
                                null,
                                instanceId,
                                product.getType(),
                                account.getId(),
                                request.getIsoCurrencyCode(),
                                String.valueOf(RegisterState.OPEN.ordinal()),
                                account.getAccountNumber()
                        );
                        productRegisterRepository.save(register);
                        registerIdList.add(register.getId());
                    }
                }
            }
        } else {
            var product = productRepository.findById(request.getInstanceId());
            if (product.isEmpty()) throw new ProductInstanceNotFoundException(request.getInstanceId());
            checkAgreements(request.getInstanceArrangement());
            for (var arrangement : request.getInstanceArrangement()) {
                var agreement = new Agreement(
                        null,
                        product.get().getId(),
                        arrangement.getGeneralAgreementId(),
                        arrangement.getSupplementaryAgreementId(),
                        arrangement.getArrangementType(),
                        arrangement.getShedulerJobId(),
                        arrangement.getNumber(),
                        arrangement.getOpeningDate().atStartOfDay(),
                        arrangement.getClosingDate().atStartOfDay(),
                        arrangement.getCancelDate().atStartOfDay(),
                        arrangement.getValidityDuration(),
                        arrangement.getCancellationReason(),
                        arrangement.getStatus(),
                        arrangement.getInterestCalculationDate().atStartOfDay(),
                        arrangement.getInterestRate(),
                        arrangement.getCoefficient(),
                        arrangement.getCoefficientAction(),
                        arrangement.getMinimumInterestRate(),
                        arrangement.getMinimumInterestRateCoefficient(),
                        arrangement.getMinimumInterestRateCoefficientAction(),
                        arrangement.getMaximalInterestRate(),
                        arrangement.getMaximalInterestRateCoefficient(),
                        arrangement.getMaximalInterestRateCoefficientAction()
                );
                agreementRepository.save(agreement);
                agreementIdList.add(agreement.getId());
            }
        }
        return new InstanceCreateResponseDto(
                new InstanceCreateResponseDataDto(
                        instanceId.toString(),
                        registerIdList,
                        agreementIdList
                )
        );
    }

    private void checkAgreements(@Valid List<InstanceArrangement> list) throws AgreementAlreadyPresentException {
        for (var arrangement : list) {
            var agreement = agreementRepository.findByNumber(arrangement.getNumber());
            if (agreement.isPresent()) {
                throw new AgreementAlreadyPresentException(arrangement.getNumber(), agreement.get().getId());
            }
        }
    }
}
