package inno.tech.controller;

import inno.tech.exceptions.AgreementAlreadyPresentException;
import inno.tech.exceptions.ContractNumberExistException;
import inno.tech.exceptions.ProductCodeNotFountException;
import inno.tech.exceptions.ProductInstanceNotFoundException;
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
                        ex.getContractNumber(), ex.getNumber()
                ));
    }

    @Nullable
    protected ResponseEntity<Object> handleProductCodeNotFountException(ProductCodeNotFountException ex,
                                                                        HttpHeaders headers, HttpStatusCode status,
                                                                        WebRequest request) {
        return ResponseEntity.badRequest().body(
                String.format("КодПродукта %s не найдено в Каталоге продуктов tpp_ref_product_class",
                        ex.getProductCode()
                ));
    }

    @Nullable
    protected ResponseEntity<Object> handleAgreementAlreadyPresentException(AgreementAlreadyPresentException ex,
                                                                            HttpHeaders headers, HttpStatusCode status,
                                                                            WebRequest request) {
        return ResponseEntity.badRequest().body(
                String.format("Параметр № Дополнительного соглашения (сделки) Number %s уже существует для ЭП с ИД  %s.",
                        ex.getNumber(), ex.getId()
                ));
    }

    @Nullable
    protected ResponseEntity<Object> handleProductInstanceNotFoundException(ProductInstanceNotFoundException ex,
                                                                            HttpHeaders headers, HttpStatusCode status,
                                                                            WebRequest request) {
        return ResponseEntity.badRequest().body(
                String.format("Экземпляр продукта с параметром instanceId %s не найден.",
                        ex.getInstanceId()
                ));
    }
}
