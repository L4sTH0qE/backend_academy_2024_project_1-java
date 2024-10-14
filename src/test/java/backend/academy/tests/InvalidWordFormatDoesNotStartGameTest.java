package backend.academy.tests;

import backend.academy.model.Category;
import backend.academy.model.Difficulty;
import backend.academy.model.GameModel;
import backend.academy.model.Word;
import backend.academy.utils.GameController;
import backend.academy.view.GameView;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

/// Проверка того, что игра не запускается, если загадываемое слово имеет некорректную длину.
public class InvalidWordFormatDoesNotStartGameTest {
    Word shortWord = new Word(Category.ANIMALS, "", Difficulty.EASY, "");
    Word incorrectWord = new Word(Category.ANIMALS, "1NCLUDES_OTH3R_SYMB*LS", Difficulty.EASY, "");
    Word longWord = new Word(Category.ANIMALS, "TWO MANY WORDS", Difficulty.EASY, "");

    GameController gameControllerShort = new GameController(new GameModel(shortWord.difficulty().attempts(), shortWord.word(), shortWord.hint()), new GameView());
    GameController gameControllerIncorrect = new GameController(new GameModel(incorrectWord.difficulty().attempts(), incorrectWord.word(), incorrectWord.hint()), new GameView());
    GameController gameControllerLong = new GameController(new GameModel(longWord.difficulty().attempts(), longWord.word(), longWord.hint()), new GameView());

    @Test
    void testInvalidWordFormat() {
        assertThrows(IllegalStateException.class, () -> gameControllerShort.startGame());
        assertThrows(IllegalStateException.class, () -> gameControllerIncorrect.startGame());
        assertThrows(IllegalStateException.class, () -> gameControllerLong.startGame());
    }
}
