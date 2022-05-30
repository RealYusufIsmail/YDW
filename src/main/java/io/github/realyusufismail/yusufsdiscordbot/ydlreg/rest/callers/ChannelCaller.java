/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.callers;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Message;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.YDLReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.guild.MessageReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.exception.RestApiException;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.name.EndPoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ChannelCaller {
    private static final Logger logger = LoggerFactory.getLogger(ChannelCaller.class);
    private final YDLReg ydl;
    private final OkHttpClient client;

    public ChannelCaller(@NotNull YDL ydl) {
        this.ydl = (YDLReg) ydl;
        this.client = ((YDLReg) ydl).getHttpClient();
    }

    @Nullable
    public Message getMessage(long channelId, long messageId) {
        Request request = new Request.Builder()
            .url(EndPoint.GET_CHANNEL_MESSAGE.getFullEndpoint(channelId, messageId))
            .get()
            .build();
        try (Response response = client.newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return null;
            } else {
                JsonNode jsonNode = ydl.getMapper().readTree(body.string());
                return new MessageReg(jsonNode, jsonNode.get("id").asLong(), ydl);
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }


}
