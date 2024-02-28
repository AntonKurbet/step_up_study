package study.stepup.lab3;

import java.util.Map;

public interface CachedAccount {
    @Cache(1000)
    String getCurrencyWithSum();
    @Cache(1000)
    boolean isCurrency(String currency);
    void setCurrency(String currency) ;
    void setSum(int sum) ;

}
