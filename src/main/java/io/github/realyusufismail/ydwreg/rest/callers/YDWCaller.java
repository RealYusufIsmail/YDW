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
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.CategoryReg;
import io.github.realyusufismail.ydwreg.rest.error.RestApiError;
import io.github.realyusufismail.ydwreg.rest.exception.RestApiException;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import io.github.realyusufismail.ydwreg.rest.request.YDWRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class YDWCaller {
    private final YDWReg ydw;

    private final OkHttpClient client;

    private final Logger logger = LoggerFactory.getLogger(YDWCaller.class);

    private final String token;


    public YDWCaller(String token, YDW ydw, OkHttpClient client) {
        this.ydw = (YDWReg) ydw;
        this.client = client;
        this.token = token;
    }

    @Nullable
    public User getUser(long id) {
        Request request = new YDWRequest().request(token, EndPoint.GET_USER.getFullEndpoint(id))
            .get()
            .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            var body = response.body();
            JsonNode jsonNode = ydw.getMapper().readTree(body.string());
            return new UserReg(jsonNode, jsonNode.get("id").asLong(), ydw);
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }

    @Nullable
    public Guild getGuild(long id) {
        Request request = new YDWRequest().request(token, EndPoint.GET_GUILD.getFullEndpoint(id))
            .get()
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage() + " "
                        + response);
            var body = response.body();
            JsonNode jsonNode = ydw.getMapper().readTree(body.string());
            return new GuildReg(jsonNode, jsonNode.get("id").asLong(), ydw);
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }

    @Nullable
    public Channel getChannel(long id) {
        Request request = new YDWRequest().request(token, EndPoint.GET_CHANNEL.getFullEndpoint(id))
            .get()
            .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            var body = response.body();
            JsonNode jsonNode = ydw.getMapper().readTree(body.string());
            return new ChannelReg(jsonNode, jsonNode.get("id").asLong(), ydw);
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }

    public YDW getYDW() {
        return ydw;
    }

    public Category getCategory(long categoryId) {
        Request request =
                new YDWRequest().request(token, EndPoint.GET_CATEGORY.getFullEndpoint(categoryId))
                    .get()
                    .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            var body = response.body();
            JsonNode jsonNode = ydw.getMapper().readTree(body.string());
            return new CategoryReg(jsonNode, jsonNode.get("id").asLong(), ydw);
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }
}
