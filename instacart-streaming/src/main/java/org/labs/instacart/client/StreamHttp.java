package org.labs.instacart.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class StreamHttp {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        try {
            String json = "";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://http2.github.io/"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> responseSync =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(responseSync.statusCode());
            System.out.println(responseSync.body());

            CompletableFuture<HttpResponse<String>> responseAsync =
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        System.out.println(response.statusCode());
                        return response;
                    });

            responseAsync.thenApply(HttpResponse::body).thenAccept(System.out::println);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
