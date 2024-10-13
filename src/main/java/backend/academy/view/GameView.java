package backend.academy.view;

import backend.academy.model.GameModel;
import backend.academy.utils.AppController;

/// Класс GameView для реализации отображения игрового процесса в консоли.
public class GameView {

    /// Метод для отображения текущего состояния игры и получения ввода от пользователя.
    @SuppressWarnings("RegexpSinglelineJava")
    public String getUpdate(GameModel gameModel) {
        this.drawHangman(gameModel.attemptsLeft());
        this.displayGameState(gameModel);
        AppView.printInput();
        String choice = AppController.SCANNER.nextLine().toUpperCase();
        System.out.println();
        AppView.clear();
        return choice;
    }

    /// Метод для завершения игровой сессии.
    @SuppressWarnings("RegexpSinglelineJava")
    public void gameOver(GameModel gameModel) {
        AppView.clear();
        System.out.println("\nGame over!");
        System.out.println(gameModel.attemptsLeft() == 0 ? "You lost :(" : "You won :)");
        drawHangman(gameModel.attemptsLeft());
        System.out.println("The word is \"" + gameModel.getWord() + "\"\n");
        System.out.print("\nPress 'Enter' to return to Main menu");
        AppView.printInput();
        AppController.SCANNER.nextLine();
        System.out.println();
    }

    /// Метод для отрисовки текущего состояния виселицы.
    @SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
    public void drawHangman(int attemptsLeft) {
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
    public void displayGameState(GameModel gameModel) {
        System.out.println("Word: " + gameModel.getCurrentWordAsString());
        System.out.println("Hint: " + gameModel.getHint());
        System.out.println("Guessed letters: " + gameModel.getGuessedLettersAsString());
        System.out.println("Attempts left: " + gameModel.attemptsLeft());
    }

    /// Метод для вывода сообщения об активации подсказки.
    @SuppressWarnings("RegexpSinglelineJava")
    public void displayHint(boolean isHintHidden) {
        if (!isHintHidden) {
            System.out.println("Hint is already activated!\n==========================");
        } else {
            System.out.println("Hint activated!\n===============");
        }
    }

    /// Метод для вывода сообщения о результате проверки введенной пользователем новой буквы.
    @SuppressWarnings("RegexpSinglelineJava")
    public void displayLetter(boolean isLetterCorrect) {
        if (isLetterCorrect) {
            System.out.println("Correct letter!\n===============");
        } else {
            System.out.println("Incorrect letter!\n=================");
        }
    }

    /// Метод для вывода сообщения о вводе уже угаданной буквы.
    @SuppressWarnings("RegexpSinglelineJava")
    public void displayGuessedLetter() {
        System.out.println("Letter is already guessed!\n==========================");
    }
}
