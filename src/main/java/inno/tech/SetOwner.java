package inno.tech;

import lombok.AllArgsConstructor;

import java.util.InvalidPropertiesFormatException;

@AllArgsConstructor
public class SetOwner implements Operation{
    private String owner;

    @Override
    public void execute(Account account) throws InvalidPropertiesFormatException {
        account.setOwner(owner);
    }
}
