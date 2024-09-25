package backend.academy.utils;

import backend.academy.model.Difficulty;
import backend.academy.model.GameState;
import backend.academy.model.Word;

/// Класс GameSession для реализации игрового процесса.
public class GameSession {

    // Константы для описания количества попыток на разных уровнях сложности.
    public static final int EASY_MAX_ATTEMPTS = 7;

    public static final int MEDIUM_MAX_ATTEMPTS = 6;

    public static final int HARD_MAX_ATTEMPTS = 5;

    // Объект для хранения информации о текущем состоянии игры.
    public static GameState gameState;

    /// Основной метод игровой сессии.
    public static void startGame(Word word) {
        Console.clear();
        int attempts = word.difficulty() == Difficulty.EASY ? EASY_MAX_ATTEMPTS : word.difficulty() == Difficulty.MEDIUM ? MEDIUM_MAX_ATTEMPTS : HARD_MAX_ATTEMPTS;
        gameState = new GameState(attempts, word.word(), word.hint());

        while (true) {
            drawHangman();
            displayGameState();
            System.out.print("\nYour input> ");
            String choice = Console.scanner.nextLine().toUpperCase();
            System.out.println();
            Console.clear();

            if (choice.length() != 1) {
                System.out.println("Invalid command.\n================");
                continue;
            }
            if (choice.equals("1")) {
                boolean flag = gameState.updateHintStatus();
                if (!flag) {
                    System.out.println("Hint is already activated!\n==========================");
                } else {
                    System.out.println("Hint activated!\n===============");
                }
                continue;
            }
            char letter = choice.charAt(0);
            if (letter >= 'A' && letter <= 'Z') {
                if (!gameState.tryGuessLetter(letter)) {
                    System.out.println("Letter is already guessed!\n==========================");
                    continue;
                }
                boolean flag = gameState.checkLetter(letter);
                if (flag) {
                    System.out.println("Correct letter!\n===============");
                } else {
                    System.out.println("Incorrect letter!\n=================");
                }
                if (gameState.checkGameStatus()) {
                    break;
                }
                continue;
            }
            System.out.println("Invalid command.\n================");
        }
        gameOver(word.word());
    }

    /// Метод для завершения игровой сессии.
    public static void gameOver(String word) {
        Console.clear();
        System.out.println("\nGame over!");
        System.out.println(gameState.attemptsLeft() == 0 ? "You lost :(" : "You won :)");
        drawHangman();
        System.out.println("The word is \"" + word + "\"\n");
        System.out.println("Press 'Enter' to return to Main menu");
        System.out.print("Your input> ");
        Console.scanner.nextLine();
        System.out.println();
    }

    /// Метод для отрисовки текущего состояния виселицы.
    public static void drawHangman() {
        switch (gameState.attemptsLeft()) {
            case 7:
                System.out.println("""
                +---+
                    |
                    |
                    |
                    |
                    |
                =========""");
                break;
            case 6:
                System.out.println("""
                +---+
                |   |
                    |
                    |
                    |
                    |
                =========""");
                break;
            case 5:
                System.out.println("""
                +---+
                |   |
                O   |
                    |
                    |
                    |
                =========""");
                break;
            case 4:
                System.out.println("""
                +---+
                |   |
                O   |
                |   |
                    |
                    |
                =========""");
                break;
            case 3:
                System.out.println("""
                +---+
                |   |
                O   |
               /|   |
                    |
                    |
                =========""");
                break;
            case 2:
                System.out.println("""
                +---+
                |   |
                O   |
               /|\\  |
                    |
                    |
                =========""");
                break;
            case 1:
                System.out.println("""
                +---+
                |   |
                O   |
               /|\\  |
               /    |
                    |
                =========""");
                break;
            case 0:
                System.out.println("""
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
                    |
                =========""");
                break;
            default:
        }
    }

    /// Метод для отрисовки текущего состояния угадываемого слова.
    public static void displayGameState() {
        System.out.println("Word: " + gameState.getCurrentWordAsString());
        System.out.println("Hint: " + gameState.getHint());
        System.out.println("Guessed letters: " + gameState.getGuessedLettersAsString());
        System.out.println("Attempts: " + gameState.attemptsLeft());
    }
}
