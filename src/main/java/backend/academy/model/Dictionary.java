package backend.academy.model;

/// Интерфейс, который описывает способ заполнения и получения списка слов для игры.
/// Реализующие его классы должны предоставлять способ получения слов извне программы.
public interface Dictionary {

    /// Метод для заполнения словаря заданным способом.
    void updateWordlist() throws Exception;

    /// Метод для получения случайного слова из словаря по заданным сложности и категории.
    Word getRandomWord(Difficulty difficulty, Category category);
}
