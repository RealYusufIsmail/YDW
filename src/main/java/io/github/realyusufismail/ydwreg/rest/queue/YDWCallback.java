package io.github.realyusufismail.ydwreg.rest.queue;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public abstract class YDWCallback implements Callback {
    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {}

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        // In most cases, this method is not needed as it can cause spammed messages in the console.
        // But if you want to use this method, just override it and do whatever you want here.
    }
}
