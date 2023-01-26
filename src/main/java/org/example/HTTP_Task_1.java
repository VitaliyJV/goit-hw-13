package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTP_Task_1 {
    public static void main(String[] args) {
        HttpClient client1 = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .GET()
                .build();
        try {
            final HttpResponse<String> response1 = client1.send(request1, HttpResponse.BodyHandlers.ofString());
            System.out.println("Headers " + response1.headers());
            System.out.println("Body " + response1.body());
            System.out.println("Status " + response1.statusCode());
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
* обновление объекта в https://jsonplaceholder.typicode.com/users. Возможно, обновления не будут видны на сайте напрямую.
Будем считать, что метод работает правильно, если в ответ на запрос придет обновленный JSON (он должен быть точно
таким же, какой вы отправляли).
* удаление объекта из https://jsonplaceholder.typicode.com/users. Здесь будем считать корректным результат - статус из
группы 2хх в ответ на запрос.
* получение информации обо всех пользователях https://jsonplaceholder.typicode.com/users
* получение информации о пользователе с определенным id https://jsonplaceholder.typicode.com/users/{id}
* получение информации о пользователе с опредленным username - https://jsonplaceholder.typicode.com/users?username={username}
 */