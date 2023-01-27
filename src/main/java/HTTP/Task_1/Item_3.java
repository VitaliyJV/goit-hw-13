package HTTP.Task_1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Item_3 {

    private static final String URL = "https://jsonplaceholder.typicode.com/users/1";

    public static void main(String[] args) {
        HttpClient client1 = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(URL))
                .DELETE()
                .build();
        try {
            final HttpResponse<String> response1 = client1.send(request1, HttpResponse.BodyHandlers.ofString());
            System.out.println("Body " + response1.body() + "\n" + "Status " + response1.statusCode() +"\n" );
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}

/*
* удаление объекта из https://jsonplaceholder.typicode.com/users. Здесь будем считать корректным результат - статус из
группы 2хх в ответ на запрос.
*/
