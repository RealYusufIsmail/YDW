/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
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
    private static final Logger logger = LoggerFactory.getLogger(ChannelCaller.class);
    private final YDWReg ydw;
    private final OkHttpClient client;
    private final String token;
    private final MediaType JSON;

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
            var body = response.body();
            JsonNode jsonNode = ydw.getMapper().readTree(body.string());
            return new MessageReg(jsonNode, jsonNode.get("id").asLong(), ydw);

        } catch (IOException e) {
            throw new RestApiException(e);
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

        /*
         * client.newCall(request).enqueue(new YDWCallback() {
         *
         * @Override public void onFailure(@NotNull Call call, @NotNull IOException e) {
         * ydw.getLogger().error("Failed to send message", e); }
         *
         * @Override public void onResponse(@NotNull Call call, @NotNull Response response) { if
         * (!response.isSuccessful()) { RestApiError error = RestApiError.fromCode(response.code());
         * ydw.getLogger().error("Failed to send message: " + error.getMessage()); } } });
         *
         */
    }

    public void sendEmbedMessage(long id, EmbedBuilder embedBuilder) {

    }
}
