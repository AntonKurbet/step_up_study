package study.stepup.lab2;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class Account implements CachedAccount {
    private String currency;
    private int sum;

    @Override
    @Cache
    public String getCurrencyWithSum() {
        return sum + " " + currency;
    }

    @Override
    @Mutator
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    @Mutator
    public void setSum(int sum) {
        this.sum = sum;
    }

}
