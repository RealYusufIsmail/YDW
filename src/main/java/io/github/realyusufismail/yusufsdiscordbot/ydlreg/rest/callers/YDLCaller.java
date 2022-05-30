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
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.Guild;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.User;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Channel;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.YDLReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.GuildReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.UserReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.guild.ChannelReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.exception.RestApiException;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.name.EndPoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class YDLCaller {
    private final YDLReg ydl;

    private final OkHttpClient client;


    public YDLCaller(YDL ydl) {
        this.ydl = (YDLReg) ydl;
        this.client = ((YDLReg) ydl).getHttpClient();
    }

    public User getUser(long id) {
        Request request =
                new Request.Builder().url(EndPoint.GET_USER.getFullEndpoint(String.valueOf(id)))
                    .get()
                    .build();
        try (Response response = client.newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return null;
            } else {
                JsonNode jsonNode = ydl.getMapper().readTree(body.string());
                return new UserReg(jsonNode, jsonNode.get("id").asLong(), ydl);
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }

    public Guild getGuild(long id) {
        Request request =
                new Request.Builder().url(EndPoint.GET_GUILD.getFullEndpoint(String.valueOf(id)))
                    .get()
                    .build();
        try (Response response = client.newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return null;
            } else {
                JsonNode jsonNode = ydl.getMapper().readTree(body.string());
                return new GuildReg(jsonNode, jsonNode.get("id").asLong(), ydl);
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }

    public Channel getChannel(long id) {
        Request request =
                new Request.Builder().url(EndPoint.GET_CHANNEL.getFullEndpoint(String.valueOf(id)))
                    .get()
                    .build();
        try (Response response = client.newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return null;
            } else {
                JsonNode jsonNode = ydl.getMapper().readTree(body.string());
                return new ChannelReg(jsonNode, jsonNode.get("id").asLong(), ydl);
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }

    public YDL getYDL() {
        return ydl;
    }
}
