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
package io.github.realyusufismail.ydwreg.rest.request;

import io.github.realyusufismail.ydw.YDWInfo;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class YDWRequest {

    public Request.Builder request(String token, String url) {
        return new Request.Builder().header("Content-Type", "application/json")
            .header("Authorization", "Bot " + token)
            .header("user-agent",
                    "DiscordBot (" + YDWInfo.ydw_GITHUB + ", " + YDWInfo.ydw_VERSION + ")")
            .header("accept-encoding", "json")
            .url(url);
    }

    public Request.Builder request(String token, HttpUrl url) {
        return new Request.Builder().header("Content-Type", "application/json")
            .header("Authorization", "Bot " + token)
            .header("user-agent",
                    "DiscordBot (" + YDWInfo.ydw_GITHUB + ", " + YDWInfo.ydw_VERSION + ")")
            .header("accept-encoding", "json")
            .url(url);
    }
}
