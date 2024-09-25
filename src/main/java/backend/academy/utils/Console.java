package backend.academy.utils;

import backend.academy.model.Category;
import backend.academy.model.Dictionary;
import backend.academy.model.Difficulty;
import lombok.extern.log4j.Log4j2;
import java.util.*;

/// Основной класс Console для работы приложения.
@Log4j2
public class Console {

    // Для получения ввода пользователя используется объект типа Scanner из состава JDK(JRE) API
    // Для инициализации scanner'а используется объект стандартного потока ввода - System.in
    public static Scanner scanner = new Scanner(System.in);

    // Экземпляр словаря для получения слова для угадывания.
    public static Dictionary dictionary;

    /// Метод для завершения работы программы.
    public static void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }

    /// Метод для очистки окна консоли.
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /// Метод для отображения окна входа в программу.
    public static void start() throws Exception {
        try {
            dictionary = new Dictionary();
            clear();
            System.out.println("Welcome to a Hangman game!\n==========================");

            // Выводим меню и ждем выбора пользователя, пока не будет выбрана опция "q" (завершение работы)
            while (true) {
                System.out.println("Main menu");
                System.out.println("1 - Start new game");
                System.out.println("q - Exit");
                System.out.print("\nYour input> ");

                String choice = Console.scanner.nextLine();
                System.out.println();

                switch (choice) {
                    case "1":
                        selectContent();
                        break;
                    case "q", "Q":
                        exit();
                        break;
                    default:
                        System.out.println("Invalid command.\n================");
                }
            }
            // Если возникла ошибка во время работы программы.
        } catch (Exception ex) {
            log.error("An unexpected error occurred while the program was running!");
            log.error("Details: {}", ex.getMessage());
            Console.exit();
        }
    }

    /// Метод для получения слова для новой игры.
    private static void selectContent() throws Exception {
        clear();
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
                System.out.print("\nYour input> ");

                String choice = Console.scanner.nextLine();
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
                        System.out.println("Invalid input.\n==============");
                }
            } while (!choiceFlag);
        } while (!settingsFlag);

        //GameSession.startGame(dictionary.getRandomWord(difficulty, category));
    }

    /// Метод для получения уровня сложности для новой игры.
    private static Difficulty getDifficulty() throws Exception {
        clear();
        Random random = new Random();
        while (true) {
            System.out.println("Choose difficulty or leave an empty input to skip this setting:");
            System.out.println("1 - Easy");
            System.out.println("2 - Medium");
            System.out.println("3 - Hard");
            System.out.println("\n* Level of difficulty affects word to guess and number of attempts *");
            System.out.print("\nYour input> ");

            String choice = Console.scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    return Difficulty.EASY;
                case "2":
                    return Difficulty.MEDIUM;
                case "3":
                    return Difficulty.HARD;
                case "":
                    return Difficulty.values()[random.nextInt(0, 3)];
                default:
                    System.out.println("Invalid command.\n================");
            }
        }
    }

    /// Метод для получения категории слова для новой игры.
    private static Category getCategory() throws Exception {
        clear();
        Random random = new Random();
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
            System.out.print("\nYour input> ");

            String choice = Console.scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    return Category.ANIMALS;
                case "2":
                    return Category.FOOD;
                case "3":
                    return Category.CLOTHES;
                case "4":
                    return Category.NATURE;
                case "5":
                    return Category.SPORTS;
                case "6":
                    return Category.TECHNOLOGY;
                case "7":
                    return Category.EMOTIONS;
                case "8":
                    return Category.JOBS;
                case "":
                    return Category.values()[random.nextInt(0, 8)];
                default:
                    System.out.println("Invalid command.\n================");
            }
        }
    }
}
