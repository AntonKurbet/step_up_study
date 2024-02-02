package inno.tech;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class SetBalance implements Operation {
    private Currency currency;
    private BigDecimal balance;

    @Override
    public void execute(Account account) {
        account.setCurrencyBalance(currency, balance);
    }
}
