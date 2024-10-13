package backend.academy.tests;

import backend.academy.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка того, что состояние игры корректно изменяется при угадывании/не угадывании.
public class GameModelChangesOnGuessTest {

    GameModel gameModel;

    @BeforeEach
    void updateGameState() {
        gameModel = new GameModel(3, "Cat", "Hint");
    }

    @Test
    void checkWordStatus() {
        gameModel.checkLetter('W');
        assertThat(gameModel.attemptsLeft()).isEqualTo(2);
        assertThat(gameModel.isGameFinished()).isEqualTo(false);

        gameModel.checkLetter('C');
        assertThat(gameModel.attemptsLeft()).isEqualTo(2);
        assertThat(gameModel.currentWord()[0]).isEqualTo('C');
        assertThat(gameModel.isGameFinished()).isEqualTo(false);

        gameModel.checkLetter('A');
        assertThat(gameModel.attemptsLeft()).isEqualTo(2);
        assertThat(gameModel.currentWord()[1]).isEqualTo('A');
        assertThat(gameModel.isGameFinished()).isEqualTo(false);

        gameModel.checkLetter('T');
        assertThat(gameModel.attemptsLeft()).isEqualTo(2);
        assertThat(gameModel.currentWord()[2]).isEqualTo('T');
        assertThat(gameModel.isGameFinished()).isEqualTo(true);
    }

    @Test
    void checkHintStatus() {
        assertThat(gameModel.getHint()).isNotEqualTo("Hint");
        assertThat(gameModel.activateHint()).isEqualTo(true);
        assertThat(gameModel.activateHint()).isEqualTo(false);
        assertThat(gameModel.getHint()).isEqualTo("Hint");
    }
}
