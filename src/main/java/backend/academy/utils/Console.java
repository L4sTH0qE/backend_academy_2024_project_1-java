package backend.academy.utils;

import lombok.extern.log4j.Log4j2;
import java.util.*;

// Основной класс Console для работы приложения.
@Log4j2
public class Console {

    // Для получения ввода пользователя используется объект типа Scanner из состава JDK(JRE) API
    // Для инициализации scanner'а используется объект стандартного потока ввода - System.in
    public static Scanner scanner = new Scanner(System.in);

    // Метод для завершения работы программы.
    public static void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }

    // Метод для очистки окна консоли.
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Метод для отображения окна входа в программу.
    public static void start() {
        try {
            clear();
            System.out.println("Welcome to a Hangman game!\n==========================");

            // Выводим меню и ждем выбора пользователя, пока не будет выбрана опция "q" (завершение работы)
            while (true) {
                System.out.println("Main menu");
                System.out.println("1 - Start new game");
                System.out.println("q - Exit");
                System.out.println();
                System.out.print("Your input> ");

                String choice = Console.scanner.nextLine();
                System.out.println();

                switch (choice) {
                    case "1":
                        getWord();
                        break;
                    case "q":
                        exit();
                        break;
                    default:
                        System.out.println("Invalid command.\n==========================");
                }
            }
            // Если возникла ошибка во время работы программы.
        } catch (Exception ex) {
            log.error("An unexpected error occurred while the program was running!");
            Console.exit();
        }
    }

    // Метод для получения слова для новой игры.
    private static void getWord() throws Exception {
        clear();
    }
}
