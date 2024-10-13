package backend.academy.tests;

import backend.academy.view.GameView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/// Проверка корректности отображения состояния игры после каждого ввода пользователя.
public class GameModelAfterUserInputTest {
    GameView gameView = new GameView();

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testFirstDisplayAfterUserInput() {
        gameView.drawHangman(7);
        assertEquals("""
                +---+
                    |
                    |
                    |
                    |
                    |
                =========""", outputStreamCaptor.toString().trim());
    }

    @Test
    void testSecondDisplayAfterUserInput() {
        gameView.drawHangman(6);
        assertEquals("""
                +---+
                |   |
                    |
                    |
                    |
                    |
                =========""", outputStreamCaptor.toString().trim());
    }

    @Test
    void testThirdDisplayAfterUserInput() {
        gameView.drawHangman(5);
        assertEquals("""
                +---+
                |   |
                O   |
                    |
                    |
                    |
                =========""", outputStreamCaptor.toString().trim());
    }

    @Test
    void testFourthDisplayAfterUserInput() {
        gameView.drawHangman(4);
        assertEquals("""
                +---+
                |   |
                O   |
                |   |
                    |
                    |
                =========""", outputStreamCaptor.toString().trim());
    }

    @Test
    void testFifthDisplayAfterUserInput() {
        gameView.drawHangman(3);
        assertEquals("""
               +---+
                |   |
                O   |
               /|   |
                    |
                    |
                =========""", outputStreamCaptor.toString().trim());
    }

    @Test
    void testSixthDisplayAfterUserInput() {
        gameView.drawHangman(2);
        assertEquals("""
               +---+
                |   |
                O   |
               /|\\  |
                    |
                    |
                =========""", outputStreamCaptor.toString().trim());
    }

    @Test
    void testSeventhDisplayAfterUserInput() {
        gameView.drawHangman(1);
        assertEquals("""
               +---+
                |   |
                O   |
               /|\\  |
               /    |
                    |
                =========""", outputStreamCaptor.toString().trim());
    }

    @Test
    void testEighthDisplayAfterUserInput() {
        gameView.drawHangman(0);
        assertEquals("""
               +---+
                |   |
                O   |
               /|\\  |
               / \\  |
                    |
                =========""", outputStreamCaptor.toString().trim());
    }
}
