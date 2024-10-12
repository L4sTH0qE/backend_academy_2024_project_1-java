package backend.academy.model;

import lombok.Getter;

/// Класс-перечисление для сложности игры.
@Getter public enum Difficulty {
    EASY(7),
    MEDIUM(6),
    HARD(5);

    // Количество попыток на заданном уровне сложности.
    private final int attempts;

    // Конструктор для инициализации количества попыток на заданном уровне сложности.
    Difficulty(int attempts) {
        this.attempts = attempts;
    }
}
