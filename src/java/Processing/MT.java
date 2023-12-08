/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processing;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author Wervy Ouzades
 */
public class MT {

    public static String createMessage(String webhookUrl, String message) {
                // Replace 'YOUR_WEBHOOK_URL' with the actual webhook URL of your Microsoft Teams channel
        //String webhookUrl = "https://caryacademy.webhook.office.com/webhookb2/cc47146c-c90b-45ec-a076-dcd19f5d6eb7@9a311c96-5070-4e97-8342-5a8bde5afbe9/IncomingWebhook/35e00bf70453468682844ad1b1a4e92d/d5ab80c8-5291-4077-9f8c-1a2498c3be1c";
        //String message = ":skull:";

        Map<String, String> payload = Map.of("text", message);

        try {
            sendMessage(webhookUrl, payload);
            return("Message sent successfully.");
        } catch (IOException e) {
            return ("Failed to send message: ") + e.getMessage();
        }
    }

    private static void sendMessage(String webhookUrl, Map<String, String> payload) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Convert the payload to JSON format
        String jsonPayload = new Gson().toJson(payload);

        // Set the media type to application/json
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        // Build the request body
        RequestBody body = RequestBody.create(mediaType, (String)(jsonPayload));
        
        
        
        // Build the request
        Request request = new Request.Builder()
                .url(webhookUrl)
                .post(body)
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }
}
