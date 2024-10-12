package backend.academy.tests;

import backend.academy.model.GameState;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка, что после превышения заданного количества попыток игра всегда возвращает поражение.
public class ExceedingAttemptsResultsInLossTest {

    @Test
    void checkFirstExceedingCase() {
        GameState gameState = new GameState(3, "Cat", "Hint");
        assertThat(gameState.attemptsLeft()).isEqualTo(3);
        assertThat(gameState.isGameFinished()).isEqualTo(false);

        gameState.checkLetter('Q');
        assertThat(gameState.attemptsLeft()).isEqualTo(2);
        assertThat(gameState.isGameFinished()).isEqualTo(false);

        gameState.checkLetter('W');
        assertThat(gameState.attemptsLeft()).isEqualTo(1);
        assertThat(gameState.isGameFinished()).isEqualTo(false);

        gameState.checkLetter('E');
        assertThat(gameState.attemptsLeft()).isEqualTo(0);
        assertThat(gameState.isGameFinished()).isEqualTo(true);
    }

    @Test
    void checkSecondExceedingCase() {
        GameState gameState = new GameState(0, "Cat", "Hint");
        assertThat(gameState.attemptsLeft()).isEqualTo(0);
        assertThat(gameState.isGameFinished()).isEqualTo(true);
    }

    @Test
    void checkThirdExceedingCase() {
        GameState gameState = new GameState(-1, "Cat", "Hint");
        assertThat(gameState.attemptsLeft()).isEqualTo(-1);
        assertThat(gameState.isGameFinished()).isEqualTo(true);
    }
}
