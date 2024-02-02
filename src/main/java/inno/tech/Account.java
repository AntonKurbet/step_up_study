package inno.tech;

import lombok.Getter;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Stack;

public class Account {

    @Getter
    private String owner;
    @Getter
    private Map<Currency, BigDecimal> balance;
    private Stack<Operation> history;

    private Account() {
    }

    public Account(String owner) throws InvalidPropertiesFormatException {
        setOwner(owner);
        balance = new HashMap<>();
        history = new Stack<>();
    }

    public void setCurrencyBalance(Currency currency, BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) throw new InvalidParameterException("Balance is negative");
        if (this.balance.get(currency) == null) {
            history.push(new RemoveCurrency(currency));
        } else {
            history.push(new SetBalance(currency,this.balance.get(currency)));
        }
        this.balance.put(currency,balance);
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
            if (!history.empty()) history.pop();
        }
    }

    public boolean canUndo() {
        return !history.empty();
    }

    public AccountState save() {
        return new AccountState(owner, new HashMap<>(balance));
    }

    public void restore(AccountState state) {
        this.owner = state.getOwner();
        this.balance = new HashMap<>(state.getBalance());
    }
}
