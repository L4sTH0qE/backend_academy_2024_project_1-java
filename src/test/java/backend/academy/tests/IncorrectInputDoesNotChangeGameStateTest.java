package backend.academy.tests;

import backend.academy.utils.GameSession;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка, что при отгадывании ввод строки длиной больше чем 1 (опечатка)
/// приводит к повторному вводу, без изменения состояния.
public class IncorrectInputDoesNotChangeGameStateTest {
    String hintInput = "1";
    String letterInput = "A";
    String symbolInput = "!";
    String longInput = "LONGINPUT";

    @Test
    void testUserInputFormat() {
        assertThat(GameSession.checkUserInput(hintInput)).isEqualTo(true);
        assertThat(GameSession.checkUserInput(letterInput)).isEqualTo(true);
        assertThat(GameSession.checkUserInput(symbolInput)).isEqualTo(false);
        assertThat(GameSession.checkUserInput(longInput)).isEqualTo(false);
    }
}
