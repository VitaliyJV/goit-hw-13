package HTTP.Task_1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Item_4 {
    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    public static void main(String[] args) {
        HttpClient client1 = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(URL))
                    .GET()
                    .build();
        try {
            final HttpResponse<String> response1 = client1.send(request1, HttpResponse.BodyHandlers.ofString());
            System.out.println("Headers " + response1.headers() + "\n" + "Body " + response1.body() + "\n" + "Status " + response1.statusCode() +"\n" );
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}

/*
 * получение информации обо всех пользователях https://jsonplaceholder.typicode.com/users
 */