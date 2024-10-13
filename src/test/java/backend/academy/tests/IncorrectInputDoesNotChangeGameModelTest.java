package backend.academy.tests;

import backend.academy.model.GameModel;
import backend.academy.utils.GameController;
import backend.academy.view.GameView;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка, что при отгадывании ввод строки длиной больше чем 1 (опечатка)
/// приводит к повторному вводу, без изменения состояния.
public class IncorrectInputDoesNotChangeGameModelTest {
    GameController gameController = new GameController(new GameModel(0, "", ""), new GameView());
    String hintInput = "1";
    String letterInput = "A";
    String symbolInput = "!";
    String longInput = "LONGINPUT";

    @Test
    void testUserInputFormat() {
        assertThat(gameController.checkUserInput(hintInput)).isEqualTo(true);
        assertThat(gameController.checkUserInput(letterInput)).isEqualTo(true);
        assertThat(gameController.checkUserInput(symbolInput)).isEqualTo(false);
        assertThat(gameController.checkUserInput(longInput)).isEqualTo(false);
    }
}
