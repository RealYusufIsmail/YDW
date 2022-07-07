package io.github.realyusufismail.ydwreg.rest.queue;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.function.Consumer;

public class Queue<T> {
    private final OkHttpClient client;
    private final Request request;
    private final Consumer<? super T> success;
    private final Consumer<? super Throwable> failure;

    public Queue(OkHttpClient client, @NotNull Request request,
            @Nullable Consumer<? super T> success, @Nullable Consumer<? super Throwable> failure) {
        this.client = client;
        this.request = request;
        this.success = success;
        this.failure = failure;
    }

    public void queue() {
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                failure.accept(e);
            }

            //TODO: broken, fix it
            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response)
                    throws IOException {
                success.accept(response.body().string());
            }
        });
    }
}
