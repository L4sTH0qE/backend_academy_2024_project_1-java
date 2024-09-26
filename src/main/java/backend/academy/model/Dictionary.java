package backend.academy.model;

import backend.academy.utils.AppService;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/// Класс Dictionary, реализующий словарь слов для игры.
public class Dictionary {

    // Константа для описания изначальной вместимости словаря.
    private static final int DICTIONARY_CAPACITY = 120;

    // Словарь.
    private final List<Word> dictionary = new ArrayList<>(DICTIONARY_CAPACITY);

    // Константы для описания полей слов из файла.
    private static final int WORD_CATEGORY = 0;
    private static final int WORD_WORD = 1;
    private static final int WORD_DIFFICULTY = 2;
    private static final int WORD_HINT = 3;

    /// Конструктор с заполнением словаря содержимым файла input.txt.
    public Dictionary() {
        updateWordlist();
    }

    /// Метод для заполнения словаря содержимым файла input.txt.
    @SuppressWarnings("RegexpSinglelineJava")
    private void updateWordlist() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("input.txt"), StandardCharsets.UTF_8)) {
            String line;
            // Парсим строки файла для заполнения словаря.
            while ((line = br.readLine()) != null) {
                String[] wordLine = line.split(";");
                Category category = Category.values()[Integer.parseInt(wordLine[WORD_CATEGORY])];
                String word = wordLine[WORD_WORD];
                Difficulty difficulty = Difficulty.values()[Integer.parseInt(wordLine[WORD_DIFFICULTY])];
                String hint = wordLine[WORD_HINT];
                dictionary.add(new Word(category, word, difficulty, hint));
            }
        } catch (IOException ex) {
            AppService.exit();
        }
    }

    /// Метод для получения случайного слова из словаря по заданным сложности и категории.
    public Word getRandomWord(Difficulty difficulty, Category category) {
        List<Word> parametrizedDictionary = dictionary.stream()
            .filter(word -> word.category() == category && word.difficulty() == difficulty)
            .toList();
        return parametrizedDictionary.get(AppService.SECURE_RANDOM.nextInt(parametrizedDictionary.size()));
    }
}
