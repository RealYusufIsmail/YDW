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

package io.github.realyusufismail.ydw.ydl.activity;

import io.github.realyusufismail.ydw.ydlreg.util.Verify;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public enum ActivityConfig {
    /**
     * Playing {name} e.g. Playing Minecraft
     */
    PLAYING(0),
    /**
     * Streaming {details} e.g. Streaming Minecraft on Twitch
     */
    STREAMING(1),
    /**
     * Listening to {name} e.g. Listening to Spotify.
     */
    LISTENING(2),
    /**
     * Watching {name} e.g. Watching lfc tv.
     */
    WATCHING(3),
    /**
     * {emoji} {name} e.g. :smiley: LFC are the best.
     */
    CUSTOM(4),
    /**
     * Competing in {name} e.g. Competing in the world cup.
     */
    COMPETING(5),
    /**
     * For future use or not implemented yet.
     */
    UNKNOWN(-1);

    private final int activity;
    private final Pattern allowedStreamingUrls =
            Pattern.compile("https?://(www\\.)?(twitch\\.tv/|youtube\\.com/watch\\?v=).+",
                    Pattern.CASE_INSENSITIVE);
    private String name;
    private String url;

    ActivityConfig(int activity) {
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
        return ActivityConfig.WATCHING.getActivity();
    }

    public int playing(@NotNull String name) {
        Verify.checkLength(name, 128);
        this.name = name;
        return ActivityConfig.PLAYING.getActivity();
    }

    public int listening(@NotNull String name) {
        Verify.checkLength(name, 128);
        this.name = name;
        return ActivityConfig.LISTENING.getActivity();
    }

    public int streaming(@NotNull String name, String url) {
        Verify.checkLength(name, 128);
        this.name = name;
        Verify.checkIfUrl(url, allowedStreamingUrls);
        this.url = url;
        return ActivityConfig.STREAMING.getActivity();
    }


    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @NotNull
    public static ActivityConfig getActivity(int activity) {
        for (ActivityConfig config : values()) {
            if (config.getActivity() == activity) {
                return config;
            }
        }
        return UNKNOWN;
    }
}
