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
