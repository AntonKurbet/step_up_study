package inno.tech.exceptions;

import lombok.Getter;

@Getter
public class ProductCodeNotFountException extends Throwable {

    private final String productCode;

    public ProductCodeNotFountException(String productCode) {
        this.productCode = productCode;
    }
}
