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
package io.github.realyusufismail.ydw;

public final class YDWInfo {
    public static final int DISCORD_REST_VERSION = 10;
    public static final int DISCORD_GATEWAY_VERSION = 10;
    public static final String ydw_VERSION = "0.0.2";

    public static final String DISCORD_GATEWAY_LINK =
            "wss://gateway.discord.gg/?v=" + DISCORD_GATEWAY_VERSION + "&encoding=json";
    public static final String DISCORD_REST_LINK =
            "https://discord.com/api/v" + DISCORD_REST_VERSION;
    public static final String ydw_GITHUB = "https://github.com/ydw";

    private YDWInfo() {}
}
