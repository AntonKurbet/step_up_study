package inno.tech;

import java.util.InvalidPropertiesFormatException;

@FunctionalInterface
public interface Operation {
    void execute(Account account) throws InvalidPropertiesFormatException;
}
