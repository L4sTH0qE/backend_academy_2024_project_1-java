package backend.academy.model;

/// Класс-запись для хранения полной информации о слове для игры.
public record Word(Category category, String word, Difficulty difficulty, String hint) {
}
