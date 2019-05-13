package org.labs.instacart.client;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow;

public class MyHandler implements java.net.http.HttpResponse.BodySubscriber<String> {
    Flow.Subscription subscription;
    private final CompletableFuture<byte[]> future = new CompletableFuture<>();

    @Override
    public CompletionStage<String> getBody() {
        return null;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(List<ByteBuffer> item) {
        item.forEach(System.out::println);

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
