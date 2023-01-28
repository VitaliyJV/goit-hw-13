package HTTP.Task_2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.stream.events.Comment;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Item_1 {
    private static final String URL = "https://jsonplaceholder.typicode.com/users/1/posts";

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        List<Post> posts = objectMapper.readValue(response.body(), new TypeReference<>() {});

        posts.add(new Post(2, 1, "title", "body"));

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
                .map(Comment::getBodyComment)
                .collect(Collectors.toList());

        System.out.println(strings);
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
