package backend.academy.utils;

import backend.academy.model.GameModel;
import backend.academy.view.AppView;
import backend.academy.view.GameView;

/// Класс GameController для взаимодействия между GameModel и GameView (логикой игры и ее отображением в консоли).
///
/// @param gameModel Объект для хранения информации о текущем состоянии игры.
/// @param gameView  Объект для отображения текущего состояния игры.
public record GameController(GameModel gameModel, GameView gameView) {

    /// Основной метод игровой сессии.
    @SuppressWarnings("RegexpSinglelineJava")
    public void startGame() {
        if (!gameModel.getWord().matches("[a-zA-Z]+")) {
            throw new IllegalStateException("Generated word has incorrect format! Please, try again later.");
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
