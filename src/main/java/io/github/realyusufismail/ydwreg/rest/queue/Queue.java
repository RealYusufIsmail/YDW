/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.rest.queue;

import io.github.realyusufismail.ydwreg.rest.error.RestApiError;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queue {
    private final OkHttpClient client;
    private final Request request;
    private final Consumer<? super Throwable> failure;
    private final Consumer<? super Response> success;

    public Queue(OkHttpClient client, @NotNull Request request,
            @Nullable Consumer<? super Throwable> failure,
            @Nullable Consumer<? super Response> success) {
        this.client = client;
        this.request = request;
        this.failure = failure;
        this.success = success;
    }

    public void queue() {
        if (failure != null && success != null) {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    failure.accept(e);
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) {
                    if (response.isSuccessful())
                        success.accept(response);
                    else
                        failure.accept(new IOException(
                                "Unexpected code " + RestApiError.fromCode(response.code()) + " "
                                        + RestApiError.fromCode(response.code()).getMessage()));
                }
            });
        } else if (failure != null) {
            client.newCall(request).enqueue(new YDWCallback() {

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    failure.accept(e);
                }
            });
        } else if (success != null) {
            client.newCall(request).enqueue(new YDWCallback() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) {
                    if (response.isSuccessful())
                        success.accept(response);
                }
            });
        } else

        {
            client.newCall(request);
        }
    }
}

