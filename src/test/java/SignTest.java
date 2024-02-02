import org.junit.jupiter.api.Test;

import static inno.tech.Main.sign;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignTest {

    @Test
    void signTest() {
        assertEquals(
                sign(-2),
                -1
        );
        assertEquals(
                sign(2),
                1
        );

    }
}
