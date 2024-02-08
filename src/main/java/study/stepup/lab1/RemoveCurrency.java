package study.stepup.lab1;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class RemoveCurrency implements Operation{

    private Currency currency;
    @Override
    public void execute(Account account) {
        account.setCurrencyBalance(currency, BigDecimal.ZERO);
    }
}
