package backend.academy.tests;

import backend.academy.model.Category;
import backend.academy.model.Dictionary;
import backend.academy.model.Difficulty;
import backend.academy.model.Word;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка правильности выбора слова из списка для всех категорий и сложностей.
public class SelectWordFromListTest {
    Dictionary dictionary = new Dictionary();

    @ParameterizedTest
    @EnumSource(Category.class)
    void getEasyWordFromList(Category category) {
        Word word = dictionary.getRandomWord(Difficulty.EASY, category);
        assertThat(word.difficulty()).isEqualTo(Difficulty.EASY);
        assertThat(word.category()).isEqualTo(category);
        assertThat(word.word()).matches("[a-zA-Z]+");
    }

    @ParameterizedTest
    @EnumSource(Category.class)
    void getMediumWordFromList(Category category) {
        Word word = dictionary.getRandomWord(Difficulty.MEDIUM, category);
        assertThat(word.difficulty()).isEqualTo(Difficulty.MEDIUM);
        assertThat(word.category()).isEqualTo(category);
        assertThat(word.word()).matches("[a-zA-Z]+");
    }

    @ParameterizedTest
    @EnumSource(Category.class)
    void getHardWordFromList(Category category) {
        Word word = dictionary.getRandomWord(Difficulty.HARD, category);
        assertThat(word.difficulty()).isEqualTo(Difficulty.HARD);
        assertThat(word.category()).isEqualTo(category);
        assertThat(word.word()).matches("[a-zA-Z]+");
    }
}
