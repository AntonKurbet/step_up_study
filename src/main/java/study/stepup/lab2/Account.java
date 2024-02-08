package study.stepup.lab2;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class Account implements CachedAccount {
    private String currency;
    private int sum;

    @Override
    public String getCurrencyWithSum() {
        return sum + " " + currency;
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
