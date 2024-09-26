package backend.academy.tests;

import backend.academy.utils.GameSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/// Проверка корректности отображения состояния игры после каждого ввода пользователя.
public class GameStateAfterUserInputTest {

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
        GameSession.drawHangman(7);
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
        GameSession.drawHangman(6);
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
        GameSession.drawHangman(5);
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
        GameSession.drawHangman(4);
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
        GameSession.drawHangman(3);
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
        GameSession.drawHangman(2);
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
        GameSession.drawHangman(1);
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
        GameSession.drawHangman(0);
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
