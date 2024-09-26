package backend.academy.tests;

import backend.academy.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка корректности обработки введенных букв вне зависимости от их регистра.
public class CaseInsensitiveInputTest {

    GameState gameState;

    @BeforeEach
    void updateGameState() {
        gameState = new GameState(3, "Cat", "Hint");
    }

    @Test
    void checkLetterUpperCase() {
        assertThat(gameState.tryGuessLetter('C')).isEqualTo(true);

        gameState.checkLetter('C');
        assertThat(gameState.attemptsLeft()).isEqualTo(3);
        assertThat(gameState.currentWord()[0]).isEqualTo('C');

        assertThat(gameState.tryGuessLetter('C')).isEqualTo(false);

        gameState.checkLetter('W');
        assertThat(gameState.attemptsLeft()).isEqualTo(2);
    }

    @Test
    void checkLetterLowerCase() {
        assertThat(gameState.tryGuessLetter('c')).isEqualTo(true);

        gameState.checkLetter('c');
        assertThat(gameState.attemptsLeft()).isEqualTo(3);
        assertThat(gameState.currentWord()[0]).isEqualTo('C');

        assertThat(gameState.tryGuessLetter('c')).isEqualTo(false);

        gameState.checkLetter('w');
        assertThat(gameState.attemptsLeft()).isEqualTo(2);
    }
}
