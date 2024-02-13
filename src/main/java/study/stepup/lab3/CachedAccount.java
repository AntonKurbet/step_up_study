package study.stepup.lab3;

import java.util.Map;

public interface CachedAccount {
    @Cache(1000)
    String getCurrencyWithSum();
    @Cache(1000)
    boolean isCurrency(String currency);
    @Mutator
    void setCurrency(String currency) ;
    @Mutator
    void setSum(int sum) ;
    @CacheGetter
    default Map<String, Object> getCache() {
        return null;
    }

}
