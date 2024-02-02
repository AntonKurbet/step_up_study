package inno.tech;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveCurrency implements Operation{

    private Currency currency;
    @Override
    public void execute(Account account) {
        account.getBalance().remove(currency);
    }
}
