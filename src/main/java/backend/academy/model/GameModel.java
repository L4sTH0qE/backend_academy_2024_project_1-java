package backend.academy.model;

import java.util.Arrays;
import java.util.stream.IntStream;
import lombok.Getter;

/// Класс GameModel для описания логики игры и текущего состояния виселицы.
public class GameModel {

    // Константа для описания длины алфавита для игры (латинского).
    private static final int ALPHABET_LENGTH = 26;

    // Плейсхолдер подсказки (если не активна).
    private static final String HINT_PLACEHOLDER = "<Enter 1 if you want to get a hint>";

    // Символ для отображения неугаданной буквы.
    private static final char HIDDEN_LETTER = '_';

    // Угаданные буквы.
    private final int[] guessedLetters = new int[ALPHABET_LENGTH];

    // Количество оставшихся попыток.
    @Getter private int attemptsLeft;

    // Текущее состояние угадываемого слова.
    @Getter private final char[] currentWord;

    // Слово для угадывания.
    private final String correctWord;

    // Подсказка к слову для угадывания.
    private final String hint;

    // Активна ли подсказка к слову для угадывания.
    private boolean isHintActive;

    /// Конструктор с инициализацией основных полей для описания состояния игры.
    public GameModel(int attemptsLeft, String correctWord, String hint) {
        this.attemptsLeft = attemptsLeft;
        this.correctWord = correctWord.toUpperCase();
        this.hint = hint;
        this.isHintActive = false;
        currentWord = new char[correctWord.length()];

        Arrays.fill(currentWord, HIDDEN_LETTER);
    }

    /// Метод для проверки, была ли буква уже угадана.
    public boolean tryGuessLetter(char ch) {
        return guessedLetters[Character.toUpperCase(ch) - 'A'] != 1;
    }

    /// Метод для проверки нахождения буквы в слове.
    public boolean checkLetter(char ch) {
        char letter = Character.toUpperCase(ch);
        guessedLetters[letter - 'A'] = 1;
        boolean isValidLetter = false;
        for (int i = 0; i < correctWord.length(); ++i) {
            if (correctWord.charAt(i) == letter) {
                isValidLetter = true;
                currentWord[i] = letter;
            }
        }
        if (!isValidLetter) {
            --attemptsLeft;
        }
        return isValidLetter;
    }

    /// Метод для проверки, была ли окончена игровая сессия.
    public boolean isGameFinished() {
        if (attemptsLeft <= 0) {
            return true;
        }
        return IntStream.range(0, currentWord.length).noneMatch(i -> currentWord[i] == HIDDEN_LETTER);
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
    public boolean activateHint() {
        if (!isHintActive) {
            isHintActive = true;
            return true;
        } else {
            return false;
        }
    }

    /// Метод для получения подсказки (или плейсхолдер, если подсказка еще не использована).
    public String getHint() {
        return isHintActive ? hint : HINT_PLACEHOLDER;
    }

    /// Метод для получения загаданного слова.
    public String getWord() {
        return correctWord;
    }
}
