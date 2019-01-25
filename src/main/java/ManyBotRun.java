import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Created by BahaWood on 1/25/19.
 */
public class ManyBotRun {

    public static void main(String args[]) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new BotContent());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
