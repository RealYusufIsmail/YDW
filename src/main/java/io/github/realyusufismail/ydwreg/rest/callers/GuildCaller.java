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
import io.github.realyusufismail.ydw.entities.guild.GuildPreview;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.guild.GuildPreviewReg;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydwreg.json.YDWJson;
import io.github.realyusufismail.ydwreg.rest.exception.InvalidJsonException;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import io.github.realyusufismail.ydwreg.rest.request.YDWRequest;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class GuildCaller {
    private final YDWReg ydw;

    @NotNull
    private final OkHttpClient client;

    private final MediaType JSON;

    private final String token;

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

    public @Nullable Member getMember(long guildId, long memberId) {
        String url = EndPoint.GET_MEMBER.getFullEndpoint(guildId, memberId);
        var request = new YDWRequest().request(token, url).get().build();

        try {
            var response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                var member = YDWJson.parseObject(response.body().string());
                return new MemberReg(member, ydw);
            }
        } catch (IOException e) {
            throw new InvalidJsonException(e);
        }
        return null;
    }

    public @Nullable Member getMember(long guildId, @NotNull String memberId) {
        return getMember(guildId, Long.parseLong(memberId));
    }
}

