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

package io.github.realyusufismail.ydw.ydlreg.rest.callers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.guild.GuildPreview;
import io.github.realyusufismail.ydw.ydl.entities.guild.Member;
import io.github.realyusufismail.ydw.ydlreg.entities.guild.GuildPreviewReg;
import io.github.realyusufismail.ydw.ydlreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydw.ydlreg.rest.exception.InvalidJsonException;
import io.github.realyusufismail.ydw.ydlreg.YDLReg;
import io.github.realyusufismail.ydw.ydlreg.json.YDLJson;
import io.github.realyusufismail.ydw.ydlreg.rest.name.EndPoint;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class GuildCaller {
    private final YDLReg ydl;

    @NotNull
    private final OkHttpClient client;

    private final MediaType JSON;

    public GuildCaller(YDL ydl, MediaType json) {
        this.ydl = (YDLReg) ydl;
        JSON = json;
        this.client = new OkHttpClient();
    }

    public @Nullable GuildPreview getGuildPreview(long guildId) {
        Request request =
                new Request.Builder().url(EndPoint.GET_GUILD_PREVIEW.getFullEndpoint(guildId))
                    .get()
                    .build();
        try {
            var response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                var json = YDLJson.parseObject(response.body().string());
                return new GuildPreviewReg(json, json.get("id").asLong(), ydl);
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
        return new Request.Builder().url(url).post(body).build();
    }

    public @NotNull Request ban(long guildId, @NotNull String userId, Integer deleteMessageDays,
            @Nullable String reason) {
        return ban(guildId, Long.parseLong(userId), deleteMessageDays, reason);
    }

    public boolean isBanned(long guildId, long userId) {
        String url = EndPoint.GET_BAN.getFullEndpoint(guildId, userId);
        Request request = new Request.Builder().url(url).get().build();
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
        return new Request.Builder().url(url).delete().build();
    }

    public @NotNull Request unBan(long guildId, @NotNull String userId) {
        return unBan(guildId, Long.parseLong(userId));
    }

    public @NotNull Request kickMember(long guildId, long userId) {
        String url = EndPoint.KICK_MEMBER.getFullEndpoint(guildId, userId);
        return new Request.Builder().url(url).delete().build();
    }

    public @NotNull Request kickMember(long guildId, @NotNull String userId) {
        return kickMember(guildId, Long.parseLong(userId));
    }

    public @Nullable Member getMember(long guildId, long memberId) {
        String url = EndPoint.GET_MEMBER.getFullEndpoint(guildId, memberId);
        var request = new Request.Builder().url(url).get().build();

        try {
            var response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                var member = YDLJson.parseObject(response.body().string());
                return new MemberReg(member, ydl);
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

