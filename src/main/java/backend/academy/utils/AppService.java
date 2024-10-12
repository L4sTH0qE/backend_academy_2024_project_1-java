package backend.academy.utils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

/// Вспомогательный класс для выделения полей и методов, необходимых для нескольких классов в отдельную сущность.
@UtilityClass
public final class AppService {

    // Для получения ввода пользователя используется объект типа Scanner из состава JDK(JRE) API
    // Для инициализации SCANNER'а используется объект стандартного потока ввода - System.in
    public static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

    // Экземпляр для генерации хорошего случайного числа.
    public static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /// Метод для завершения работы программы.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }

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
}
