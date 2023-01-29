package HTTP.Task_2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Item_1 {
    private static final String URL = "https://jsonplaceholder.typicode.com/users/1/posts";

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Post> posts = objectMapper.readValue(response.body(), new TypeReference<>() {});

        Optional<Integer> max = posts.stream()
                .map(Post::getPostId)
                .max(Integer::compareTo);

        String URL = "https://jsonplaceholder.typicode.com/posts/".concat(max.get().toString()).concat("/comments");

        HttpRequest secondRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<String> secondResponse =
        client.send(secondRequest, HttpResponse.BodyHandlers.ofString());
        List<Comment> comments = objectMapper.readValue(secondResponse.body(), new TypeReference<>() {});
        List<String> strings = comments.stream()
                .map(Comment::getBody)
                .collect(Collectors.toList());

        var numOfComment= comments.stream()
                .map(Comment::getId)
                .max(Integer::compareTo);


        String resultFileName = String.format("User-%s-post-%s-comments.json", max.get(), numOfComment.get());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(strings);
        File jsonFileFinish = new File("C:\\Users\\Vitaliy\\goit-hw-13\\src\\main\\resources\\" + resultFileName);
        try {
            if (jsonFileFinish.createNewFile()){
                System.out.println("File jsonFileFinish is created!");
            }
            else{
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(jsonFileFinish);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Objects.requireNonNull(writer).write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Objects.requireNonNull(writer).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

/*
Задание 2
Дополните программу методом, который будет выводить все комментарии к последнему посту определенного пользователя и
записывать их в файл.
https://jsonplaceholder.typicode.com/users/1/posts Последним будем считать пост с наибольшим id.
https://jsonplaceholder.typicode.com/posts/10/comments
Файл должен называться "user-X-post-Y-comments.json", где Х - номер пользователя, Y - номер поста.
 */
