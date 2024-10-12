package backend.academy.utils;

import backend.academy.model.Difficulty;
import backend.academy.model.GameState;
import backend.academy.model.Word;
import lombok.Getter;
import lombok.experimental.UtilityClass;

/// Класс GameSession для реализации игрового процесса.
@UtilityClass
public final class GameSession {

    // Константы для описания количества попыток на разных уровнях сложности.
    private static final int EASY_MAX_ATTEMPTS = 7;

    private static final int MEDIUM_MAX_ATTEMPTS = 6;

    private static final int HARD_MAX_ATTEMPTS = 5;

    // Объект для хранения информации о текущем состоянии игры.
    @Getter private static GameState gameState;

    /// Основной метод игровой сессии.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void startGame(Word word) throws IllegalArgumentException {
        AppService.clear();
        if (!word.word().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Generated word has incorrect format! Please, try again later.");
        }

        int attempts = word.difficulty() == Difficulty.EASY ? EASY_MAX_ATTEMPTS
            : word.difficulty() == Difficulty.MEDIUM ? MEDIUM_MAX_ATTEMPTS : HARD_MAX_ATTEMPTS;
        gameState = new GameState(attempts, word.word(), word.hint());

        while (true) {
            drawHangman(gameState.attemptsLeft());
            displayGameState();
            AppService.printInput();
            String choice = AppService.SCANNER.nextLine().toUpperCase();
            System.out.println();
            AppService.clear();

            if (!checkUserInput(choice)) {
                AppService.printInvalidCommand();
                continue;
            }
            char letter = choice.charAt(0);
            if (letter == '1') {
                boolean flag = gameState.activateHint();
                if (!flag) {
                    System.out.println("Hint is already activated!\n==========================");
                } else {
                    System.out.println("Hint activated!\n===============");
                }
                continue;
            }
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
                if (gameState.isGameFinished()) {
                    break;
                }
                continue;
            }
            AppService.printInvalidCommand();
        }
        gameOver(word.word());
    }

    /// Метод для завершения игровой сессии.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void gameOver(String word) {
        AppService.clear();
        System.out.println("\nGame over!");
        System.out.println(gameState.attemptsLeft() == 0 ? "You lost :(" : "You won :)");
        drawHangman(gameState.attemptsLeft());
        System.out.println("The word is \"" + word + "\"\n");
        System.out.print("\nPress 'Enter' to return to Main menu");
        AppService.printInput();
        AppService.SCANNER.nextLine();
        System.out.println();
    }

    /// Метод для отрисовки текущего состояния виселицы.
    @SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
    public static void drawHangman(int attemptsLeft) {
        switch (attemptsLeft) {
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
    @SuppressWarnings("RegexpSinglelineJava")
    public static void displayGameState() {
        System.out.println("Word: " + gameState.getCurrentWordAsString());
        System.out.println("Hint: " + gameState.getHint());
        System.out.println("Guessed letters: " + gameState.getGuessedLettersAsString());
        System.out.println("Attempts left: " + gameState.attemptsLeft());
    }

    /// Метод для проверки пользовательского ввода во время игры.
    public static boolean checkUserInput(String userInput) {
        if (userInput.length() != 1) {
            return false;

        }
        char letter = userInput.charAt(0);
        return letter == '1' || (letter >= 'A' && letter <= 'Z');
    }
}
