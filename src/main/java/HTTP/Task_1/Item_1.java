package HTTP.Task_1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;

public class Item_1 {
    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    public static void main(String[] args) {
        HttpClient client1 = HttpClient.newHttpClient();
        HttpRequest request1 = null;
        try {
            request1 = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(URL))
                    .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("src/main/resources/user.json")))
                    .GET()
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
Задание 1
Программа должна содержать методы для реализации следующего функционала:
* создание нового объекта в https://jsonplaceholder.typicode.com/users. Возможно, вы не увидите обновлений на сайте.
Метод создания работает правильно, если в ответ на JSON с объектом вернулся такой же JSON, но с полем id со значением
на 1 больше, чем самый большой id на сайте.
 */