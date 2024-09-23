package backend.academy;

import backend.academy.utils.Console;
import lombok.extern.log4j.Log4j2;
import lombok.experimental.UtilityClass;

@UtilityClass
@Log4j2
public class Main {
    public static void main(String[] args) {
        // Запуск стартового окна.
        try {
            Console.start();
        } // Если возникла ошибка во время работы программы.
        catch (Exception ex) {
            log.error("An unexpected error occurred while the program was running!");
            Console.exit();
        }
    }
}
