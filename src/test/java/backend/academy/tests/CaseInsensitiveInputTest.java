package backend.academy.tests;

import backend.academy.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка корректности обработки введенных букв вне зависимости от их регистра.
public class CaseInsensitiveInputTest {

    GameModel gameModel;

    @BeforeEach
    void updateGameState() {
        gameModel = new GameModel(3, "Cat", "Hint");
    }

    @Test
    void checkTrueLetterUpperCase() {
        assertThat(gameModel.tryGuessLetter('C')).isEqualTo(true);
        gameModel.checkLetter('C');
        assertThat(gameModel.attemptsLeft()).isEqualTo(3);
        assertThat(gameModel.currentWord()[0]).isEqualTo('C');
    }

    @Test
    void checkFalseLetterUpperCase() {
        gameModel.checkLetter('C');
        assertThat(gameModel.tryGuessLetter('C')).isEqualTo(false);
        gameModel.checkLetter('W');
        assertThat(gameModel.attemptsLeft()).isEqualTo(2);
    }

    @Test
    void checkTrueLetterLowerCase() {
        assertThat(gameModel.tryGuessLetter('c')).isEqualTo(true);
        gameModel.checkLetter('c');
        assertThat(gameModel.attemptsLeft()).isEqualTo(3);
        assertThat(gameModel.currentWord()[0]).isEqualTo('C');
    }

    @Test
    void checkFalseLetterLowerCase() {
        gameModel.checkLetter('c');
        assertThat(gameModel.tryGuessLetter('c')).isEqualTo(false);
        gameModel.checkLetter('w');
        assertThat(gameModel.attemptsLeft()).isEqualTo(2);
    }
}
