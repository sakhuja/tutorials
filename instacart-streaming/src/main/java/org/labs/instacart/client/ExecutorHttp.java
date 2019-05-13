package org.labs.instacart.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ExecutorHttp {
    public static void main(String[] args) {
        do1();
        do2();
        do3();
    }

    private static void do3() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.google.com"))
                .GET()
                .header("User-Agent", "Java 11")
                .build();
        try {
            HttpResponse<String> response = client.send(request, responseInfo -> new MyHandler());
            System.out.println(response.statusCode());
            System.out.println(response.uri());
            System.out.println(response.body());
            System.out.println(response.headers());
            System.out.println(response.version());
            System.out.println(Optional.of(response.previousResponse()));

        } catch (IOException e) {
            System.out.println("Something wrong here!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void do1() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.google.com"))
                .GET()
                .header("User-Agent", "Java 11")
                .build();
        try {
            HttpResponse<String> response = client.send(request,
                    responseInfo -> responseInfo.statusCode() == 200 ?
                            HttpResponse.BodySubscribers.ofString(Charset.defaultCharset()) :
                            HttpResponse.BodySubscribers.replacing("Something wrong there!")
            );
            System.out.println(response.statusCode());

            do2();

        } catch (IOException e) {
            System.out.println("Something wrong here!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void do2() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.google.com"))
                .GET()
                .header("User-Agent", "Java 11")
                .build();
        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request,
                responseInfo -> responseInfo.statusCode() == 200 ?
                        HttpResponse.BodySubscribers.ofString(Charset.defaultCharset()) :
                        HttpResponse.BodySubscribers.replacing("Something wrong there!")
        );
        responseFuture
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> "Something wrong here!")
                .thenAccept(System.out::println);

    }
}
