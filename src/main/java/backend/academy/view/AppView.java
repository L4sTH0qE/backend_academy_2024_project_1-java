package backend.academy.view;

import backend.academy.model.Category;
import backend.academy.model.Difficulty;
import java.util.Map;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

/// Вспомогательный класс для выделения методов вывода в консоль, необходимых для использования в нескольких классах.
@UtilityClass
public class AppView {

    /// Метод для очистки окна консоли.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /// Метод для вывода сообщения об ошибке.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void printInvalidCommand() {
        System.out.println("Invalid command.\n================");
    }

    /// Метод для вывода сообщения об ожидании ввода пользователя.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void printInput() {
        System.out.print("\nYour input> ");
    }

    /// Метод для вывода сообщения при запуске приложения.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void printEntryMessage() {
        System.out.println("Welcome to a Hangman game!\n==========================");
    }

    /// Метод для вывода сообщения о завершении работы программы.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void exit() {
        System.out.println("Exiting...");
    }

    /// Метод для вывода сообщения обо всех значениях в мапе с их ключами.
    @SuppressWarnings("RegexpSinglelineJava")
    public static <T extends Enum<T>> void printMap(Map<String, T> enumMap) {
        for (Map.Entry<String, T> entry : enumMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().name());
        }
    }

    /// Метод для вывода главного меню и получения выбора опции от пользователя.
    @SuppressWarnings("RegexpSinglelineJava")
    public static String getOptionFromUser(Scanner scanner) {
        System.out.println("Main menu");
        System.out.println("1 - Start new game");
        System.out.println("q - Exit");
        AppView.printInput();
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    /// Метод для вывода сообщения об ожидании выбора пользователем уровня сложности.
    @SuppressWarnings("RegexpSinglelineJava")
    public static String getDifficultyFromUser(Map<String, Difficulty> difficultyMap, Scanner scanner) {
        System.out.println("Choose difficulty or leave an empty input to skip this setting:");
        printMap(difficultyMap);
        System.out.println("\n* Level of difficulty affects word to guess and number of attempts *");
        AppView.printInput();
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    /// Метод для вывода сообщения об ожидании выбора пользователем категории слова.
    @SuppressWarnings("RegexpSinglelineJava")
    public static String getCategoryFromUser(Map<String, Category> categoryMap, Scanner scanner) {
        System.out.println("Choose word category or leave an empty input to skip this setting:");
        AppView.printMap(categoryMap);
        AppView.printInput();
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    /// Метод для вывода сообщения об ожидании подтверждения выбранных опций для игры пользователем.
    @SuppressWarnings("RegexpSinglelineJava")
    public static String getConfirmFromUser(Difficulty difficulty, Category category, Scanner scanner) {
        System.out.println(
            "Game settings: Difficulty - " + difficulty.name() + ", Word category - " + category.name() + ".");
        System.out.println("Do you want to keep these settings (y/n)?");
        AppView.printInput();
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }
}
