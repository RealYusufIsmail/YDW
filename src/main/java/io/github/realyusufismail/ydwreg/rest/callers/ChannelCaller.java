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
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.guild.MessageReg;
import io.github.realyusufismail.ydwreg.rest.exception.RestApiException;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
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
    private final YDWReg ydw;
    private final OkHttpClient client;

    public ChannelCaller(@NotNull YDW ydw) {
        this.ydw = (YDWReg) ydw;
        this.client = ((YDWReg) ydw).getHttpClient();
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
                JsonNode jsonNode = ydw.getMapper().readTree(body.string());
                return new MessageReg(jsonNode, jsonNode.get("id").asLong(), ydw);
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }


}
