package backend.academy.model;

import java.io.IOException;

/// Интерфейс, который описывает способ заполнения и получения списка слов для игры.
/// Реализующие его классы должны предоставлять способ получения слов извне программы.
public interface Dictionary {

    /// Метод для заполнения словаря заданным способом.
    void updateWordlist() throws IOException;

    /// Метод для получения случайного слова из словаря по заданным сложности и категории.
    Word getRandomWord(Difficulty difficulty, Category category);
}
