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
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.rest.error.RestApiError;
import io.github.realyusufismail.ydwreg.rest.exception.RestApiException;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import io.github.realyusufismail.ydwreg.rest.request.YDWRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCaller {
    private final YDWReg ydw;

    private final OkHttpClient client;

    private final String token;

    public UserCaller(String token, @Nullable YDWReg ydw, OkHttpClient client) {
        this.ydw = ydw;
        this.client = client;
        this.token = token;
    }

    @NotNull
    public List<Guild> getGuilds() {
        Request request =
                new YDWRequest().request(token, EndPoint.GET_CURRENT_USER_GUILDS.getEndpoint())
                    .get()
                    .build();
        try (var response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());
            var body = response.body();
            JsonNode json = ydw.getMapper().readTree(body.string());
            List<Guild> guilds = new ArrayList<>();
            for (JsonNode node : json) {
                guilds.add(new GuildReg(node, node.get("id").asLong(), ydw));
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
        return new ArrayList<>();
    }
}
