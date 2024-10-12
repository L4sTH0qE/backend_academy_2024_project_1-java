package backend.academy.model;

import lombok.Getter;

/// Класс для описания текущего состояния, количества сделанных попыток,
/// максимального количества попыток и сообщений для пользователя.
public class GameState {

    // Константа для описания длины алфавита для игры (латинского).
    private static final int ALPHABET_LENGTH = 26;

    // Угаданные буквы.
    private int[] guessedLetters = new int[ALPHABET_LENGTH];

    // Количество оставшихся попыток.
    @Getter private int attemptsLeft;

    // Текущее состояние угадываемого слова.
    @Getter private char[] currentWord;

    // Слово для угадывания.
    private final String correctWord;

    // Подсказка к слову для угадывания.
    private final String hint;

    // Плейсхолдер подсказки (если не активна).
    private final String hintPlaceholder = "<Enter 1 if you want to get a hint>";

    // Активна ли подсказка к слову для угадывания.
    private boolean isHintActive;

    /// Конструктор с инициализацией основных полей для описания состояния игры.
    public GameState(int attemptsLeft, String correctWord, String hint) {
        this.attemptsLeft = attemptsLeft;
        this.correctWord = correctWord.toUpperCase();
        this.hint = hint;
        this.isHintActive = false;
        currentWord = new char[correctWord.length()];

        for (int i = 0; i < correctWord.length(); ++i) {
            currentWord[i] = '_';
        }
    }

    /// Метод для проверки, была ли буква уже угадана.
    public boolean tryGuessLetter(char ch) {
        return guessedLetters[Character.toUpperCase(ch) - 'A'] != 1;
    }

    /// Метод для проверки нахождения буквы в слове.
    public boolean checkLetter(char ch) {
        char letter = Character.toUpperCase(ch);
        guessedLetters[letter - 'A'] = 1;
        boolean flag = false;
        for (int i = 0; i < correctWord.length(); ++i) {
            if (correctWord.charAt(i) == letter) {
                flag = true;
                currentWord[i] = letter;
            }
        }
        if (!flag) {
            --attemptsLeft;
        }
        return flag;
    }

    /// Метод для проверки, была ли окончена игровая сессия.
    public boolean checkGameStatus() {
        if (attemptsLeft <= 0) {
            return true;
        }

        for (char c : currentWord) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    /// Метод для получения строкового представления угаданных букв.
    public String getGuessedLettersAsString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < guessedLetters.length; ++i) {
            if (guessedLetters[i] == 1) {
                res.append((char) ('A' + i)).append(' ');
            }
        }
        return res.toString();
    }

    /// Метод для получения строкового представления угаданного слова.
    public String getCurrentWordAsString() {
        return String.valueOf(currentWord);
    }

    /// Метод для обновления статуса отображения подсказки (если еще не была обновлена).
    public boolean updateHintStatus() {
        if (!isHintActive) {
            isHintActive = true;
            return true;
        }
        return false;
    }

    /// Метод для получения подсказки (или плейсхолдер, если подсказка еще не использована).
    public String getHint() {
        if (isHintActive) {
            return hint;
        }
        return hintPlaceholder;
    }
}
