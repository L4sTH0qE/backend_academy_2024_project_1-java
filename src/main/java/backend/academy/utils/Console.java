package backend.academy.utils;

import backend.academy.model.Category;
import backend.academy.model.Dictionary;
import backend.academy.model.Difficulty;
import lombok.extern.log4j.Log4j2;

/// Основной класс Console для работы приложения.
@Log4j2
public final class Console {

    // Конструктор.
    private Console() {
    }

    // Экземпляр словаря для получения слова для угадывания.
    private static Dictionary dictionary;

    /// Метод для отображения окна входа в программу.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void start() throws Exception {
        try {
            dictionary = new Dictionary();
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
            System.out.println("1 - Easy");
            System.out.println("2 - Medium");
            System.out.println("3 - Hard");
            System.out.println("\n* Level of difficulty affects word to guess and number of attempts *");
            AppService.printInput();

            String choice = AppService.SCANNER.nextLine();
            System.out.println();
            Difficulty difficulty = Difficulty.EASY;
            boolean flag = true;

            switch (choice) {
                case "1":
                    difficulty = Difficulty.EASY;
                    break;
                case "2":
                    difficulty = Difficulty.MEDIUM;
                    break;
                case "3":
                    difficulty = Difficulty.HARD;
                    break;
                case "":
                    difficulty = Difficulty.values()[AppService.SECURE_RANDOM.nextInt(Difficulty.values().length)];
                    break;
                default:
                    AppService.printInvalidCommand();
                    flag = false;
            }
            if (flag) {
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
            System.out.println("1 - Animals");
            System.out.println("2 - Food");
            System.out.println("3 - Clothes");
            System.out.println("4 - Nature");
            System.out.println("5 - Sports");
            System.out.println("6 - Technology");
            System.out.println("7 - Emotions");
            System.out.println("8 - Jobs");
            AppService.printInput();

            String choice = AppService.SCANNER.nextLine();
            System.out.println();
            Category category = Category.ANIMALS;
            boolean flag = true;

            switch (choice) {
                case "1":
                    category = Category.ANIMALS;
                    break;
                case "2":
                    category = Category.FOOD;
                    break;
                case "3":
                    category = Category.CLOTHES;
                    break;
                case "4":
                    category = Category.NATURE;
                    break;
                case "5":
                    category = Category.SPORTS;
                    break;
                case "6":
                    category = Category.TECHNOLOGY;
                    break;
                case "7":
                    category = Category.EMOTIONS;
                    break;
                case "8":
                    category = Category.JOBS;
                    break;
                case "":
                    category = Category.values()[AppService.SECURE_RANDOM.nextInt(Category.values().length)];
                    break;
                default:
                    AppService.printInvalidCommand();
                    flag = false;
            }
            if (flag) {
                return category;
            }
        }
    }
}
