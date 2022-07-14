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
import io.github.realyusufismail.ydw.application.Interaction;
import io.github.realyusufismail.ydw.application.commands.ApplicationCommand;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.application.commands.ApplicationCommandReg;
import io.github.realyusufismail.ydwreg.rest.error.RestApiError;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import io.github.realyusufismail.ydwreg.rest.request.YDWRequest;
import okhttp3.*;

import java.io.IOException;

public class InteractionCaller {

    private final YDWReg ydw;
    private final OkHttpClient client;
    private final MediaType JSON;
    private final String token;
    private ResponseBody body = null;

    public InteractionCaller(YDWReg ydw, OkHttpClient client, MediaType json, String token) {
        this.ydw = ydw;
        this.client = client;
        JSON = json;
        this.token = token;
    }

    public ApplicationCommand getApplication(long applicationId, long commandId,
            Interaction interaction) {

        Request request =
                new YDWRequest()
                    .request(token,
                            EndPoint.GET_APPLICATION_COMMAND.getFullEndpoint(applicationId,
                                    commandId))
                    .get()
                    .build();

        try (var response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());

            // check for a 404 or 200

            if (response.code() == 404) {
                return getGuildApplication(applicationId, commandId);
            } else if (response.code() == 200) {
                return getGlobalApplication(interaction, response);
            } else {
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (body != null)
                body.close();
        }

        return null;
    }

    private ApplicationCommand getGlobalApplication(Interaction interaction, Response response)
            throws IOException {
        var body = response.body();
        JsonNode json = ydw.getMapper().readTree(body.string());
        return new ApplicationCommandReg(json, json.get("id").asLong(), interaction, ydw);
    }

    private ApplicationCommand getGuildApplication(long applicationId, long commandId) {

        return null;
    }

    public Interaction getInteraction(JsonNode json) {
        return null;
    }
}
