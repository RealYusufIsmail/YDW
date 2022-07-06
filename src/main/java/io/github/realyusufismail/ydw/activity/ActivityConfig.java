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

package io.github.realyusufismail.ydw.activity;

import io.github.realyusufismail.ydwreg.util.Verify;
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

    @NotNull
    public static ActivityConfig getActivity(int activity) {
        for (ActivityConfig config : values()) {
            if (config.getActivity() == activity) {
                return config;
            }
        }
        return UNKNOWN;
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
}
