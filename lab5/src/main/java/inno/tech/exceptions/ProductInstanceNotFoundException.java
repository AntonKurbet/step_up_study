package inno.tech.exceptions;

import lombok.Getter;

@Getter
public class ProductInstanceNotFoundException extends Throwable {
    private final Integer instanceId;

    public ProductInstanceNotFoundException(Integer instanceId) {
        this.instanceId = instanceId;
    }
}
