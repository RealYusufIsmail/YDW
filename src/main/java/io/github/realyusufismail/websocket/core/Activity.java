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

package io.github.realyusufismail.websocket.core;

import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydlreg.util.Verify;

import java.util.regex.Pattern;

public enum Activity {
    PLAYING(0),
    STREAMING(1),
    LISTENING(2),
    WATCHING(3),
    COMPETING(5);

    private final int activity;
    private final Pattern allowedStreamingUrls =
            Pattern.compile("https?://(www\\.)?(twitch\\.tv/|youtube\\.com/watch\\?v=).+",
                    Pattern.CASE_INSENSITIVE);
    private String name;
    private String url;

    Activity(int activity) {
        this.activity = activity;
    }

    public int getActivity() {
        return activity;
    }

    public int watching(@NotNull String name, String url) {
        Verify.checkLength(name, 128);
        this.name = name;
        Verify.checkIfUrl(url, allowedStreamingUrls);
        this.url = url;
        return Activity.WATCHING.getActivity();
    }

    public int playing(@NotNull String name) {
        Verify.checkLength(name, 128);
        this.name = name;
        return Activity.PLAYING.getActivity();
    }

    public int listening(@NotNull String name) {
        Verify.checkLength(name, 128);
        this.name = name;
        return Activity.LISTENING.getActivity();
    }

    public int streaming(@NotNull String name, String url) {
        Verify.checkLength(name, 128);
        this.name = name;
        Verify.checkIfUrl(url, allowedStreamingUrls);
        this.url = url;
        return Activity.STREAMING.getActivity();
    }


    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
