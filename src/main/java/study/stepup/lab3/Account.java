package study.stepup.lab3;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Account implements CachedAccount {
    private String currency;
    private int sum;

    @Override
    public String getCurrencyWithSum() {
        return sum + " " + currency;
    }

    @Override
    public boolean isCurrency(String currency) {
        return this.currency.equals(currency);
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public void setSum(int sum) {
        this.sum = sum;
    }

}
