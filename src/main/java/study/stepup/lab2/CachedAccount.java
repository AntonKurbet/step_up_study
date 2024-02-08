package study.stepup.lab2;

import java.util.Map;

public interface CachedAccount {
    String getCurrencyWithSum();
    void setCurrency(String currency) ;
    void setSum(int sum) ;
    default Map<String, Object> getCache() {
        return null;
    }

    ;
}
