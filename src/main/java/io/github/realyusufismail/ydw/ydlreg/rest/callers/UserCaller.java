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
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.Guild;
import io.github.realyusufismail.ydw.ydlreg.entities.GuildReg;
import io.github.realyusufismail.ydw.ydlreg.rest.exception.RestApiException;
import io.github.realyusufismail.ydw.ydlreg.YDLReg;
import io.github.realyusufismail.ydw.ydlreg.rest.name.EndPoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCaller {
    private final YDLReg ydl;

    private final OkHttpClient client;

    public UserCaller(@NotNull YDL ydl) {
        this.ydl = (YDLReg) ydl;
        this.client = ((YDLReg) ydl).getHttpClient();
    }

    @NotNull
    public List<Guild> getGuilds() {
        Request request = new Request.Builder().url(EndPoint.GET_CURRENT_USER_GUILDS.getEndpoint())
            .get()
            .build();
        try (var response = client.newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return new ArrayList<>();
            } else {
                JsonNode json = ydl.getMapper().readTree(body.string());
                List<Guild> guilds = new ArrayList<>();
                for (JsonNode node : json) {
                    guilds.add(new GuildReg(node, node.get("id").asLong(), ydl));
                }
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
        return new ArrayList<>();
    }
}
