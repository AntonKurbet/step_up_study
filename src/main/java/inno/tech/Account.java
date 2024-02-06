package inno.tech;

import lombok.Getter;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;

public class Account {

    @Getter
    private String owner;
    private Map<Currency, BigDecimal> balance;
    private ArrayDeque<Operation> history;

    private Account() {
    }

    public Account(String owner) throws InvalidPropertiesFormatException {
        setOwner(owner);
        balance = new HashMap<>();
        history = new ArrayDeque<>();
    }

    public Map<Currency, BigDecimal> getBalance() {
        return Collections.unmodifiableMap(balance);
    }

    public void setCurrencyBalance(Currency currency, BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) throw new InvalidParameterException("Balance is negative");
        if (this.balance.get(currency) == null) {
            history.push(new RemoveCurrency(currency));
        } else {
            history.push(new SetBalance(currency, this.balance.get(currency)));
        }
        if (balance.equals(BigDecimal.ZERO)) {
            this.balance.remove(currency);
        } else {
            this.balance.put(currency, balance);
        }
    }

    public void setOwner(String owner) throws InvalidPropertiesFormatException {
        if (owner == null || owner.isEmpty()) throw new InvalidPropertiesFormatException("owner is blank");
        if (this.owner != null) {
            history.push(new SetOwner(this.owner));
        }
        this.owner = owner;
    }

    public void undo() throws InvalidPropertiesFormatException {
        if (history != null && !history.isEmpty()) {
            Operation undo = history.pop();
            undo.execute(this);
            if (!history.isEmpty()) history.pop();
        }
    }

    public boolean canUndo() {
        return !history.isEmpty();
    }

    public AccountState save() {
        return new AccountState(owner, new HashMap<>(balance));
    }

    public static Account restore(AccountState state) throws InvalidPropertiesFormatException {
        var result = new Account(state.getOwner());
        for (var e : state.getBalance().entrySet()) {
            result.setCurrencyBalance(e.getKey(), e.getValue());
        }
        return result;
    }
}
