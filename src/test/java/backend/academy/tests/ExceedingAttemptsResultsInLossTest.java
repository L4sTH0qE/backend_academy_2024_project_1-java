package backend.academy.tests;

import backend.academy.model.GameModel;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка, что после превышения заданного количества попыток игра всегда возвращает поражение.
public class ExceedingAttemptsResultsInLossTest {

    @Test
    void checkFirstExceedingCase() {
        GameModel gameModel = new GameModel(3, "Cat", "Hint");
        assertThat(gameModel.attemptsLeft()).isEqualTo(3);
        assertThat(gameModel.isGameFinished()).isEqualTo(false);

        gameModel.checkLetter('Q');
        assertThat(gameModel.attemptsLeft()).isEqualTo(2);
        assertThat(gameModel.isGameFinished()).isEqualTo(false);

        gameModel.checkLetter('W');
        assertThat(gameModel.attemptsLeft()).isEqualTo(1);
        assertThat(gameModel.isGameFinished()).isEqualTo(false);

        gameModel.checkLetter('E');
        assertThat(gameModel.attemptsLeft()).isEqualTo(0);
        assertThat(gameModel.isGameFinished()).isEqualTo(true);
    }

    @Test
    void checkSecondExceedingCase() {
        GameModel gameModel = new GameModel(0, "Cat", "Hint");
        assertThat(gameModel.attemptsLeft()).isEqualTo(0);
        assertThat(gameModel.isGameFinished()).isEqualTo(true);
    }

    @Test
    void checkThirdExceedingCase() {
        GameModel gameModel = new GameModel(-1, "Cat", "Hint");
        assertThat(gameModel.attemptsLeft()).isEqualTo(-1);
        assertThat(gameModel.isGameFinished()).isEqualTo(true);
    }
}
