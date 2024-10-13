package backend.academy.utils;

import backend.academy.model.Category;
import backend.academy.model.Dictionary;
import backend.academy.model.Difficulty;
import backend.academy.model.FileDictionary;
import backend.academy.model.GameModel;
import backend.academy.model.Word;
import backend.academy.view.AppView;
import backend.academy.view.GameView;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

/// Основной класс Console для работы приложения.
@Log4j2
@UtilityClass
public final class AppController {

    // Для получения ввода пользователя используется объект типа Scanner из состава JDK(JRE) API
    // Для инициализации SCANNER'а используется объект стандартного потока ввода - System.in
    public static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

    // Экземпляр для генерации хорошего случайного числа.
    public static final SecureRandom SECURE_RANDOM = new SecureRandom();

    // Экземпляр словаря для получения слова для угадывания.
    private static Dictionary dictionary;

    // Используем статическую мапу для получения уровня сложности по ключу-строке.
    private static Map<String, Difficulty> difficultyMap = new HashMap<>();

    // Используем статическую мапу для получения категории слова по ключу-строке.
    private static Map<String, Category> categoryMap = new HashMap<>();

    /// Метод для отображения окна входа в программу.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void start() throws Exception {
        try {
            AppView.clear();
            AppView.printEntryMessage();

            // Выводим меню и ждем выбора пользователя, пока не будет выбрана опция "q" (завершение работы)
            while (true) {
                String choice = AppView.getOptionFromUser(SCANNER);
                switch (choice) {
                    case "1":
                        selectContent();
                        break;
                    case "q", "Q":
                        AppController.exit();
                        break;
                    default:
                        AppView.printInvalidCommand();
                }
            }
            // Если возникла ошибка во время работы программы.
        } catch (Exception ex) {
            log.error("An unexpected error occurred while the program was running!");
            log.error("Details: {}", ex.getMessage());
            AppController.exit();
        }
    }

    /// Метод для получения слова для новой игры.
    @SuppressWarnings("RegexpSinglelineJava")
    private static void selectContent() throws Exception {
        AppView.clear();
        boolean settingsFlag = false;
        Difficulty difficulty;
        Category category;
        do {
            difficulty = getDifficulty();
            category = getCategory();

            boolean choiceFlag = false;
            do {
                String choice = AppView.getConfirmFromUser(difficulty, category, SCANNER);
                switch (choice) {
                    case "y", "Y":
                        settingsFlag = true;
                        choiceFlag = true;
                        break;
                    case "n", "N":
                        choiceFlag = true;
                        break;
                    default:
                        AppView.printInvalidCommand();
                }
            } while (!choiceFlag);
        } while (!settingsFlag);
        AppView.clear();
        Word word = dictionary.getRandomWord(difficulty, category);
        GameModel gameModel = new GameModel(word.difficulty().attempts(), word.word(), word.hint());
        GameView gameView = new GameView();
        GameController gameController = new GameController(gameModel, gameView);
        gameController.startGame();
    }

    /// Метод для получения уровня сложности для новой игры.
    @SuppressWarnings("RegexpSinglelineJava")
    private static Difficulty getDifficulty() throws Exception {
        AppView.clear();
        while (true) {
            String choice = AppView.getDifficultyFromUser(difficultyMap, SCANNER);
            Difficulty difficulty;

            if (choice.isEmpty()) {
                difficulty = Difficulty.values()[SECURE_RANDOM.nextInt(Difficulty.values().length)];
            } else {
                difficulty = difficultyMap.getOrDefault(choice, null);
            }
            if (difficulty == null) {
                AppView.printInvalidCommand();
            } else {
                return difficulty;
            }
        }
    }

    /// Метод для получения категории слова для новой игры.
    @SuppressWarnings("RegexpSinglelineJava")
    private static Category getCategory() throws Exception {
        AppView.clear();
        while (true) {
            String choice = AppView.getCategoryFromUser(categoryMap, SCANNER);
            Category category;

            if (choice.isEmpty()) {
                category = Category.values()[SECURE_RANDOM.nextInt(Category.values().length)];
            } else {
                category = categoryMap.getOrDefault(choice, null);
            }
            if (category == null) {
                AppView.printInvalidCommand();
            } else {
                return category;
            }
        }
    }

    /// Метод для инициализации основных полей для работы с меню игры.
    public static void initialiseData(String[] args) throws Exception {
        // Механика заполнения словаря для игры словами из файла, указанного в args.
        String filePath = args[0];
        Path basePath = Paths.get("data/");
        Path resolvedPath = basePath.resolve(filePath).normalize();
        if (!resolvedPath.startsWith(basePath)) {
            throw new Exception("Invalid file path");
        }

        dictionary = new FileDictionary(resolvedPath);
        dictionary.updateWordlist();

        // Заполняем мапы.
        for (Difficulty difficulty: Difficulty.values()) {
            difficultyMap.put(String.valueOf(difficulty.ordinal() + 1), difficulty);
        }
        for (Category category: Category.values()) {
            categoryMap.put(String.valueOf(category.ordinal() + 1), category);
        }
    }

    /// Метод для завершения работы программы.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void exit() {
        AppView.exit();
        System.exit(0);
    }
}
