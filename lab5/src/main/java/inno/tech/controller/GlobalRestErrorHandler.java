package inno.tech.controller;

import inno.tech.exceptions.ContractNumberExistException;
import inno.tech.exceptions.ProductCodeNotFountException;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalRestErrorHandler extends ResponseEntityExceptionHandler {

    @Nullable
    protected ResponseEntity<Object> handleContractNumberExistException(ContractNumberExistException ex,
                                                                        HttpHeaders headers, HttpStatusCode status,
                                                                        WebRequest request) {
        return ResponseEntity.badRequest().body(
                String.format("Параметр ContractNumber № договора %s уже существует для ЭП с ИД  %s",
//                        request.getContractNumber(), request.getInstanceId()
                        1, 2
                ));
    }

    @Nullable
    protected ResponseEntity<Object> handleProductCodeNotFountException(ProductCodeNotFountException ex,
                                                                        HttpHeaders headers, HttpStatusCode status,
                                                                        WebRequest request) {
        return ResponseEntity.badRequest().body(
                String.format("КодПродукта %s не найдено в Каталоге продуктов tpp_ref_product_class",
//                        request.getProductCode()
                        1
                ));
    }
}
