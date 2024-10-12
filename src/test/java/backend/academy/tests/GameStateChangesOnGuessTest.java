package backend.academy.tests;

import backend.academy.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка того, что состояние игры корректно изменяется при угадывании/не угадывании.
public class GameStateChangesOnGuessTest {

    GameState gameState;

    @BeforeEach
    void updateGameState() {
        gameState = new GameState(3, "Cat", "Hint");
    }

    @Test
    void checkWordStatus() {
        gameState.checkLetter('W');
        assertThat(gameState.attemptsLeft()).isEqualTo(2);
        assertThat(gameState.checkGameStatus()).isEqualTo(false);

        gameState.checkLetter('C');
        assertThat(gameState.attemptsLeft()).isEqualTo(2);
        assertThat(gameState.currentWord()[0]).isEqualTo('C');
        assertThat(gameState.checkGameStatus()).isEqualTo(false);

        gameState.checkLetter('A');
        assertThat(gameState.attemptsLeft()).isEqualTo(2);
        assertThat(gameState.currentWord()[1]).isEqualTo('A');
        assertThat(gameState.checkGameStatus()).isEqualTo(false);

        gameState.checkLetter('T');
        assertThat(gameState.attemptsLeft()).isEqualTo(2);
        assertThat(gameState.currentWord()[2]).isEqualTo('T');
        assertThat(gameState.checkGameStatus()).isEqualTo(true);
    }

    @Test
    void checkHintStatus() {
        assertThat(gameState.getHint()).isNotEqualTo("Hint");
        assertThat(gameState.activateHint()).isEqualTo(true);
        assertThat(gameState.activateHint()).isEqualTo(false);
        assertThat(gameState.getHint()).isEqualTo("Hint");
    }
}
