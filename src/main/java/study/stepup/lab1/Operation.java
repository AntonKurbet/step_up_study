package study.stepup.lab1;

import java.util.InvalidPropertiesFormatException;

@FunctionalInterface
public interface Operation {
    void execute(Account account) throws InvalidPropertiesFormatException;
}
