package HTTP.Task_3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class Item_1 {
    private static final String URL = "https://jsonplaceholder.typicode.com/users/1/todos";
    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();

        List<Todo> todos = objectMapper.readValue(response.body(), new TypeReference<>() {
        });

        List<Todo> completedTodos = todos.stream()
                .filter(Todo::isCompleted)
                .collect(Collectors.toList());

        System.out.println(completedTodos);
    }
}

/*
Дополните программу методом, который будет выводить все открытые задачи для пользователя Х.
https://jsonplaceholder.typicode.com/users/1/todos.
Открытыми считаются все задачи, у которых completed = false.
 */
