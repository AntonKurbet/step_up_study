package inno.tech;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
public class AccountState {
    private final String owner;
    private final Map<Currency, BigDecimal> balance;

    public AccountState(String owner, HashMap<Currency, BigDecimal> balance) {
        this.owner = owner;
        this.balance = balance;
    }
}
