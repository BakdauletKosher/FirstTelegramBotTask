import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by BahaWood on 1/25/19.
 */
public class BotContent extends TelegramLongPollingBot {

    public String getBotToken() {
        return "787619798:AAEhOHsbDtvjPQG2vKN2Il_rOhIf2dxIMyw";
    }

    public void onUpdateReceived(Update update) {

        SendMessage message = new SendMessage();
        String command = update.getMessage().getText();

        if (command.equals("/courseusd")) {
            message.setText(getUsdToKzt());
        }

        message.setChatId(update.getMessage().getChatId());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "Many Bot";
    }

    public String getUsdToKzt() {

        String url = "http://www.apilayer.net/api/live?access_key=bc5c2d1a23b8cfd97c77e0aaeb896221&format=1";
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject myResponse = new JSONObject(response.toString());
            String usd = myResponse.getJSONObject("quotes").get("USDKZT").toString();
            return "USD -> KZT = " + usd;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Bot doesn't work, now";
    }
}