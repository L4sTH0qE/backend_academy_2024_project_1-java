package backend.academy;

import backend.academy.utils.AppService;
import backend.academy.utils.Console;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public class Main {
    public static void main(String[] args) {
        // Запуск стартового окна.
        try {
            Console.start();
        } catch (Exception ex) { // Если возникла ошибка во время работы программы.
            log.error("An unexpected error occurred while the program was running!");
            AppService.exit();
        }
    }
}
