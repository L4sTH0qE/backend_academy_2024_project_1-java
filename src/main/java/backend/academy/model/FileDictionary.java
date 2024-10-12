package backend.academy.model;

import backend.academy.utils.AppService;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/// Класс FileDictionary, реализующий интерфейс словаря со словами для игры.
public class FileDictionary implements Dictionary {

    // Словарь.
    private final List<Word> dictionary = new ArrayList<>();

    // Путь до файла со словами.
    private final Path filePath;

    /// Конструктор с заполнением словаря содержимым файла input.txt.
    public FileDictionary(Path filePath) {
        this.filePath = filePath;
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    public void updateWordlist() {
        try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            // Парсим строки файла для заполнения словаря.
            while ((line = br.readLine()) != null) {
                String[] wordLine = line.split(";");
                Category category = Category.values()[Integer.parseInt(wordLine[WordInfo.CATEGORY.ordinal()])];
                String word = wordLine[WordInfo.WORD.ordinal()];
                Difficulty difficulty = Difficulty.values()[Integer.parseInt(wordLine[WordInfo.DIFFICULTY.ordinal()])];
                String hint = wordLine[WordInfo.HINT.ordinal()];
                dictionary.add(new Word(category, word, difficulty, hint));
            }
        } catch (IOException ex) {
            AppService.exit();
        }
    }

    public Word getRandomWord(Difficulty difficulty, Category category) {
        List<Word> parametrizedDictionary = dictionary.stream()
            .filter(word -> word.category() == category && word.difficulty() == difficulty)
            .toList();
        return parametrizedDictionary.get(AppService.SECURE_RANDOM.nextInt(parametrizedDictionary.size()));
    }
}
