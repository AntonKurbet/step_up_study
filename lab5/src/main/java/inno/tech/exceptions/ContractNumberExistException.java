package inno.tech.exceptions;

import lombok.Getter;

@Getter
public class ContractNumberExistException extends Throwable {
    private final String contractNumber;
    private final String number;

    public ContractNumberExistException(String contractNumber, String number) {
        this.contractNumber = contractNumber;
        this.number = number;
    }
}
