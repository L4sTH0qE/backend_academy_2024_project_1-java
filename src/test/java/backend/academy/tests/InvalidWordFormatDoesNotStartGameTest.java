package backend.academy.tests;

import backend.academy.model.Category;
import backend.academy.model.Difficulty;
import backend.academy.model.Word;
import backend.academy.utils.GameSession;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

/// Проверка того, что игра не запускается, если загадываемое слово имеет некорректную длину.
public class InvalidWordFormatDoesNotStartGameTest {
    Word shortWord = new Word(Category.ANIMALS, "", Difficulty.EASY, "");
    Word incorrectWord = new Word(Category.ANIMALS, "1NCLUDES_OTH3R_SYMB*LS", Difficulty.EASY, "");
    Word longWord = new Word(Category.ANIMALS, "TWO MANY WORDS", Difficulty.EASY, "");

    @Test
    void testInvalidWordFormat() {
        assertThrows(IllegalArgumentException.class, () -> GameSession.startGame(shortWord));
        assertThrows(IllegalArgumentException.class, () -> GameSession.startGame(incorrectWord));
        assertThrows(IllegalArgumentException.class, () -> GameSession.startGame(longWord));
    }
}
