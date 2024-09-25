package backend.academy.model;

import java.io.*;
import java.util.*;

/// Класс Dictionary, реализующий словарь слов для игры.
public class Dictionary {
    // Словарь.
    private static final List<Word> dictionary = new ArrayList<>();

    /// Конструктор с заполнением словаря содержимым файла input.txt.
    public Dictionary() {
        updateWordlist();
    }

    /// Метод для заполнения словаря содержимым файла input.txt.
    private void updateWordlist() {
        // Путь до файла со словами для игры.
        String filePath = "input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Парсим строки файла для заполнения словаря.
            while ((line = br.readLine()) != null) {
                String[] wordLine = line.split(";");
                Category category = Category.values()[Integer.parseInt(wordLine[0])];
                String word = wordLine[1];
                Difficulty difficulty = Difficulty.values()[Integer.parseInt(wordLine[2])];
                String hint = wordLine[3];
                dictionary.add(new Word(category, word, difficulty, hint));
            }
        } catch (Exception ex) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    /// Метод для получения случайного слова из словаря по заданным сложности и категории.
    public Word getRandomWord(Difficulty difficulty, Category category) {
        List<Word> parametrizedDictionary = dictionary.stream()
            .filter(word -> word.category() == category && word.difficulty() == difficulty)
            .toList();
        Random random = new Random();
        return parametrizedDictionary.get(random.nextInt(0, parametrizedDictionary.size()));
    }
}
