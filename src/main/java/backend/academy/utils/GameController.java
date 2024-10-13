package backend.academy.utils;

import backend.academy.model.GameModel;
import backend.academy.view.AppView;
import backend.academy.view.GameView;
import lombok.Getter;

/// Класс GameController для взаимодействия между GameModel и GameView (логикой игры и ее отображением в консоли).
@Getter public final class GameController {

    // Объект для хранения информации о текущем состоянии игры.
    private GameModel gameModel;

    // Объект для отображения текущего состояния игры.
    private GameView gameView;

    // Конструктор.
    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;
    }

    /// Основной метод игровой сессии.
    @SuppressWarnings("RegexpSinglelineJava")
    public void startGame() throws IllegalArgumentException {
        if (!gameModel.getWord().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Generated word has incorrect format! Please, try again later.");
        }
        while (true) {
            String choice = gameView.getUpdate(gameModel);
            if (!checkUserInput(choice)) {
                AppView.printInvalidCommand();
                continue;
            }
            char letter = choice.charAt(0);
            if (letter == '1') {
                gameView.displayHint(gameModel.activateHint());
                continue;
            }
            if (letter >= 'A' && letter <= 'Z') {
                if (!gameModel.tryGuessLetter(letter)) {
                    gameView.displayGuessedLetter();
                    continue;
                }
                gameView.displayLetter(gameModel.checkLetter(letter));
                if (gameModel.isGameFinished()) {
                    break;
                }
            }
        }
        gameView.gameOver(gameModel);
    }

    /// Метод для проверки пользовательского ввода во время игры.
    public boolean checkUserInput(String userInput) {
        if (userInput.length() != 1) {
            return false;
        }
        char letter = userInput.charAt(0);
        return letter == '1' || (letter >= 'A' && letter <= 'Z');
    }
}
