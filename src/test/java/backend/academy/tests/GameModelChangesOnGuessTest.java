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
    void checkFalseLetterWordStatus() {
        gameModel.checkLetter('W');
        assertThat(gameModel.attemptsLeft()).isEqualTo(2);
        assertThat(gameModel.isGameFinished()).isEqualTo(false);
    }

    @Test
    void checkTrueLetterWordStatus() {
        gameModel.checkLetter('C');
        assertThat(gameModel.attemptsLeft()).isEqualTo(3);
        assertThat(gameModel.isGameFinished()).isEqualTo(false);
    }

    @Test
    void checkTrueLastLetterWordStatus() {
        gameModel.checkLetter('C');
        gameModel.checkLetter('A');
        gameModel.checkLetter('T');
        assertThat(gameModel.attemptsLeft()).isEqualTo(3);
        assertThat(gameModel.currentWord()[0]).isEqualTo('C');
        assertThat(gameModel.currentWord()[1]).isEqualTo('A');
        assertThat(gameModel.currentWord()[2]).isEqualTo('T');
        assertThat(gameModel.isGameFinished()).isEqualTo(true);
    }

    @Test
    void checkTrueActivationHintStatus() {
        assertThat(gameModel.activateHint()).isEqualTo(true);
        assertThat(gameModel.getHint()).isEqualTo("Hint");
    }

    @Test
    void checkFalseActivationHintStatus() {
        assertThat(gameModel.getHint()).isNotEqualTo("Hint");
        gameModel.activateHint();
        assertThat(gameModel.activateHint()).isEqualTo(false);
    }
}
