package inno.tech.services;

import inno.tech.dto.InstanceCreateRequestDto;
import inno.tech.dto.InstanceCreateResponseDto;
import inno.tech.entity.Product;
import inno.tech.exceptions.ContractNumberExistException;
import inno.tech.exceptions.ProductCodeNotFountException;
import inno.tech.repositories.ProductRepository;
import inno.tech.repositories.RefProductClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductInstanceService {

    public static final String CLIENT_ACCOUNT_TYPE = "Клиентский";
    ProductRepository productRepository;
    RefProductClassRepository refProductClassRepository;

    public InstanceCreateResponseDto create(InstanceCreateRequestDto request) throws ContractNumberExistException, ProductCodeNotFountException {
        if (request.getInstanceId() == null) {
            if (productRepository.findByNumber(request.getContractNumber()).isPresent()) {
                throw new ContractNumberExistException();
            }
            var productClass = refProductClassRepository.findByValue(request.getProductCode());
            if (productClass.isPresent()) {
                var types = productClass.get().getRegisterTypes().stream()
                        .filter(t -> t.getAccountType().equals(CLIENT_ACCOUNT_TYPE)).toList();
                if (types.isEmpty()) {
                    throw new ProductCodeNotFountException();
                }
                productRepository.save(new Product(
                        null,
                        request.getProductCode()

                ));
            }
        } else {
            return null;
        }
        return null;
    }
}
