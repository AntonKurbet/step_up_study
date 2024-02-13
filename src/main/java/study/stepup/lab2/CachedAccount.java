package study.stepup.lab2;

import java.util.Map;

public interface CachedAccount {
    @Cache
    String getCurrencyWithSum();
    @Mutator
    void setCurrency(String currency) ;
    @Mutator
    void setSum(int sum) ;
    @CacheGetter
    default Map<String, Object> getCache() {
        return null;
    }

}
