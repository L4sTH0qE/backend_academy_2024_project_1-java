package backend.academy.utils;

import backend.academy.model.Category;
import backend.academy.model.Dictionary;
import backend.academy.model.Difficulty;
import backend.academy.model.FileDictionary;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

/// Основной класс Console для работы приложения.
@Log4j2
@UtilityClass
public final class Console {

    // Экземпляр словаря для получения слова для угадывания.
    private static Dictionary dictionary;

    // Используем статическую мапу для получения уровня сложности по ключу-строке.
    private static Map<String, Difficulty> difficultyMap = new HashMap<>();

    // Используем статическую мапу для получения категории слова по ключу-строке.
    private static Map<String, Category> categoryMap = new HashMap<>();

    /// Метод для отображения окна входа в программу.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void start(String[] args) throws Exception {
        try {
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

            AppService.clear();
            System.out.println("Welcome to a Hangman game!\n==========================");

            // Выводим меню и ждем выбора пользователя, пока не будет выбрана опция "q" (завершение работы)
            while (true) {
                System.out.println("Main menu");
                System.out.println("1 - Start new game");
                System.out.println("q - Exit");
                AppService.printInput();

                String choice = AppService.SCANNER.nextLine();
                System.out.println();

                switch (choice) {
                    case "1":
                        selectContent();
                        break;
                    case "q", "Q":
                        AppService.exit();
                        break;
                    default:
                        AppService.printInvalidCommand();
                }
            }
            // Если возникла ошибка во время работы программы.
        } catch (Exception ex) {
            log.error("An unexpected error occurred while the program was running!");
            log.error("Details: {}", ex.getMessage());
            AppService.exit();
        }
    }

    /// Метод для получения слова для новой игры.
    @SuppressWarnings("RegexpSinglelineJava")
    private static void selectContent() throws Exception {
        AppService.clear();
        boolean settingsFlag = false;
        Difficulty difficulty;
        Category category;
        do {
            difficulty = getDifficulty();
            category = getCategory();

            boolean choiceFlag = false;
            do {
                System.out.println(
                    "Game settings: Difficulty - " + difficulty.name() + ", Word category - " + category.name() + ".");
                System.out.println("Do you want to keep these settings (y/n)?");
                AppService.printInput();

                String choice = AppService.SCANNER.nextLine();
                System.out.println();

                switch (choice) {
                    case "y", "Y":
                        settingsFlag = true;
                        choiceFlag = true;
                        break;
                    case "n", "N":
                        choiceFlag = true;
                        break;
                    default:
                        AppService.printInvalidCommand();
                }
            } while (!choiceFlag);
        } while (!settingsFlag);
        GameSession.startGame(dictionary.getRandomWord(difficulty, category));
    }

    /// Метод для получения уровня сложности для новой игры.
    @SuppressWarnings("RegexpSinglelineJava")
    private static Difficulty getDifficulty() throws Exception {
        AppService.clear();
        while (true) {
            System.out.println("Choose difficulty or leave an empty input to skip this setting:");
            printMap(difficultyMap);
            System.out.println("\n* Level of difficulty affects word to guess and number of attempts *");
            AppService.printInput();

            String choice = AppService.SCANNER.nextLine();
            System.out.println();
            Difficulty difficulty;

            if (choice.isEmpty()) {
                difficulty = Difficulty.values()[AppService.SECURE_RANDOM.nextInt(Difficulty.values().length)];
            } else {
                difficulty = difficultyMap.getOrDefault(choice, null);
            }
            if (difficulty == null) {
                AppService.printInvalidCommand();
            } else {
                return difficulty;
            }
        }
    }

    /// Метод для получения категории слова для новой игры.
    @SuppressWarnings("RegexpSinglelineJava")
    private static Category getCategory() throws Exception {
        AppService.clear();
        while (true) {
            System.out.println("Choose word category or leave an empty input to skip this setting:");
            printMap(categoryMap);
            AppService.printInput();

            String choice = AppService.SCANNER.nextLine();
            System.out.println();
            Category category;

            if (choice.isEmpty()) {
                category = Category.values()[AppService.SECURE_RANDOM.nextInt(Category.values().length)];
            } else {
                category = categoryMap.getOrDefault(choice, null);
            }
            if (category == null) {
                AppService.printInvalidCommand();
            } else {
                return category;
            }
        }
    }

    /// Метод для вывода сообщения обо всех значениях в мапе с их ключами.
    @SuppressWarnings("RegexpSinglelineJava")
    private static <T extends Enum<T>> void printMap(Map<String, T> enumMap) {
        for (Map.Entry<String, T> entry : enumMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().name());
        }
    }
}
