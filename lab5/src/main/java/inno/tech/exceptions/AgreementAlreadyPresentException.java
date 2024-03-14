package inno.tech.exceptions;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class AgreementAlreadyPresentException extends Throwable {
    private final String number;
    private final Integer id;

    public AgreementAlreadyPresentException(@NotEmpty String number, Integer id) {
        this.number = number;
        this.id = id;
    }
}
