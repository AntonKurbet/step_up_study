package study.stepup.lab1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignTest {

    @Test
    void signTest() {
        Assertions.assertEquals(
                Main.sign(-2),
                -1
        );
        Assertions.assertEquals(
                Main.sign(2),
                1
        );

    }
}
