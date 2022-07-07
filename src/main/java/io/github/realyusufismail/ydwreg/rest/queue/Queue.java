package io.github.realyusufismail.ydwreg.rest.queue;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.function.Consumer;

public class Queue {
    private final OkHttpClient client;
    private final Request request;
    private final Consumer<? super Throwable> failure;

    public Queue(OkHttpClient client, @NotNull Request request,
            @Nullable Consumer<? super Throwable> failure) {
        this.client = client;
        this.request = request;
        this.failure = failure;
    }

    public void queue() {
        if (failure != null) {
            client.newCall(request).enqueue(new YDWCallback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    failure.accept(e);
                }
            });
        } else {
            client.newCall(request);
        }
    }
}

