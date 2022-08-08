/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
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
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.guild.GuildPreview;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.guild.GuildPreviewReg;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydwreg.json.YDWJson;
import io.github.realyusufismail.ydwreg.rest.error.RestApiError;
import io.github.realyusufismail.ydwreg.rest.exception.InvalidJsonException;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import io.github.realyusufismail.ydwreg.rest.request.YDWRequest;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TODO: Create a system which checks if the required intents are enabled.
public class GuildCaller {

    private final YDWReg ydw;
    @NotNull
    private final OkHttpClient client;
    private final MediaType JSON;
    private final String token;
    private ResponseBody body = null;

    public GuildCaller(String token, @Nullable YDW ydw, MediaType json, OkHttpClient client) {
        this.ydw = (YDWReg) ydw;
        JSON = json;
        this.client = client;
        this.token = token;
    }

    public @Nullable GuildPreview getGuildPreview(long guildId) {
        Request request =
                new YDWRequest().request(token, EndPoint.GET_GUILD_PREVIEW.getFullEndpoint(guildId))
                    .get()
                    .build();
        try {
            var response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                var json = YDWJson.parseObject(response.body().string());
                return new GuildPreviewReg(json, json.get("id").asLong(), ydw);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (body != null)
                body.close();
        }
        return null;
    }

    public @NotNull Request ban(long guildId, long userId, @Nullable Integer deleteMessageDays,
            @Nullable String reason) {
        int days = deleteMessageDays == null ? 0 : deleteMessageDays;
        String reasonString = reason == null ? "" : reason;
        JsonNode json = JsonNodeFactory.instance.objectNode()
            .put("delete_message_days", days)
            .put("reason", reasonString);

        RequestBody body = RequestBody.create(json.toString(), JSON);
        String url = EndPoint.BAN_USER.getFullEndpoint(guildId, userId);
        return new YDWRequest().request(token, url).post(body).build();
    }

    public @NotNull Request ban(long guildId, @NotNull String userId, Integer deleteMessageDays,
            @Nullable String reason) {
        return ban(guildId, Long.parseLong(userId), deleteMessageDays, reason);
    }

    public boolean isBanned(long guildId, long userId) {
        String url = EndPoint.GET_BAN.getFullEndpoint(guildId, userId);
        Request request = new YDWRequest().request(token, url).get().build();
        try {
            client.newCall(request).execute();
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (body != null)
                body.close();
        }
    }

    public boolean isBanned(long guildId, @NotNull String userId) {
        return isBanned(guildId, Long.parseLong(userId));
    }

    public @NotNull Request unBan(long guildId, long userId) {
        String url = EndPoint.UNBAN_USER.getFullEndpoint(guildId, userId);
        return new YDWRequest().request(token, url).delete().build();
    }

    public @NotNull Request unBan(long guildId, @NotNull String userId) {
        return unBan(guildId, Long.parseLong(userId));
    }

    public @NotNull Request kickMember(long guildId, long userId) {
        String url = EndPoint.KICK_MEMBER.getFullEndpoint(guildId, userId);
        return new YDWRequest().request(token, url).delete().build();
    }

    public @NotNull Request kickMember(long guildId, @NotNull String userId) {
        return kickMember(guildId, Long.parseLong(userId));
    }

    public @NotNull Member getMember(long guildId, long memberId) {
        String url = EndPoint.GET_MEMBER.getFullEndpoint(guildId, memberId);
        var request = new YDWRequest().request(token, url).get().build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());

            var member = YDWJson.parseObject(response.body().string());
            return new MemberReg(member, ydw);
        } catch (IOException e) {
            throw new InvalidJsonException(e);
        } finally {
            if (body != null)
                body.close();
        }
    }

    public @NotNull Member getMember(long guildId, @NotNull String memberId) {
        return getMember(guildId, Long.parseLong(memberId));
    }

    public Channel getChannel(Long idLong, long channelIdLong) {
        String url = EndPoint.GET_GUILD_CHANNEL.getFullEndpoint(idLong, channelIdLong);
        var request = new YDWRequest().request(token, url).get().build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());

            var channel = YDWJson.parseObject(response.body().string());
            return new ChannelReg(channel, channel.get("id").asLong(), ydw);
        } catch (IOException e) {
            throw new InvalidJsonException(e);
        } finally {
            if (body != null)
                body.close();
        }
    }

    public List<Channel> getChannels(Long idLong) {
        String url = EndPoint.GET_GUILD_CHANNELS.getFullEndpoint(idLong);
        var request = new YDWRequest().request(token, url).get().build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());

            body = response.body();
            JsonNode json = ydw.getMapper().readTree(body.string());
            List<Channel> channels = new ArrayList<>();
            for (JsonNode channel : json) {
                channels.add(new ChannelReg(channel, channel.get("id").asLong(), ydw));
            }
            return channels;
        } catch (IOException e) {
            throw new InvalidJsonException(e);
        } finally {
            if (body != null)
                body.close();
        }
    }

    public List<Member> getGuildMembers(Long idLong) {
        String url = EndPoint.GET_GUILD_MEMBERS.getFullEndpoint(idLong);
        HttpUrl urlBuilder =
                HttpUrl.parse(url).newBuilder().addQueryParameter("limit", "1000").build();
        var request = new YDWRequest().request(token, urlBuilder.toString()).get().build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());

            body = response.body();
            JsonNode json = ydw.getMapper().readTree(body.string());
            List<Member> members = new ArrayList<>();
            for (JsonNode member : json) {
                members.add(new MemberReg(member, ydw));
            }
            return members;
        } catch (IOException e) {
            throw new InvalidJsonException(e);
        } finally {
            if (body != null)
                body.close();
        }
    }
}

