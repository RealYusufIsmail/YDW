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
package io.github.realyusufismail.ydwreg.rest.callers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.entities.guild.MessageReg;
import io.github.realyusufismail.ydwreg.rest.error.RestApiError;
import io.github.realyusufismail.ydwreg.rest.exception.RestApiException;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import io.github.realyusufismail.ydwreg.rest.request.YDWRequest;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ChannelCaller {

    private final YDWReg ydw;
    private final OkHttpClient client;
    private final String token;
    private final MediaType JSON;
    private ResponseBody body = null;

    public ChannelCaller(String token, @NotNull YDW ydw, OkHttpClient client, MediaType json) {
        this.ydw = (YDWReg) ydw;
        this.client = client;
        this.token = token;
        JSON = json;
    }

    @Nullable
    public Message getMessage(long channelId, long messageId) {
        Request request = new YDWRequest()
            .request(token, EndPoint.GET_CHANNEL_MESSAGE.getFullEndpoint(channelId, messageId))
            .get()
            .build();


        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());
            body = response.body();
            JsonNode jsonNode = ydw.getMapper().readTree(body.string());
            return new MessageReg(jsonNode, jsonNode.get("id").asLong(), ydw);
        } catch (IOException e) {
            throw new RestApiException(e);
        } finally {
            if (body != null)
                body.close();
        }
    }


    public Request sendMessage(long channelId, @NotNull String message) {
        if (message.length() > 2000)
            throw new RestApiException("Message is too long");

        JsonNode json = JsonNodeFactory.instance.objectNode().put("content", message);

        RequestBody body = RequestBody.create(json.toString(), JSON);

        return new YDWRequest().request(token, EndPoint.CREATE_MESSAGE.getFullEndpoint(channelId))
            .post(body)
            .build();
    }

    // TODO: Implement this method
    public void sendEmbedMessage(long id, EmbedBuilder embedBuilder) {

    }
}
