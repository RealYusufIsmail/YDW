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

package io.github.realyusufismail.ydw.entities;

import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydw.entities.guild.channel.NewsChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.StageChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.VoiceChannel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Category extends Channel {
    @NotNull
    default List<Channel> getChannels() {
        List<Channel> channels = new ArrayList<>();
        channels.addAll(getTextChannel());
        channels.addAll(getVoiceChannel());
        channels.addAll(getNewsChannel());
        channels.addAll(getStageChannel());
        Collections.sort(channels);
        return channels;
    }

    @NotNull
    default List<TextChannel> getTextChannel() {
        return Collections.unmodifiableList(getGuild().getTextChannels()
            .stream()
            .filter(channel -> equals(channel.getParentCategory()))
            .sorted()
            .toList());
    }

    @NotNull
    default List<VoiceChannel> getVoiceChannel() {
        return Collections.unmodifiableList(getGuild().getVoiceChannels()
            .stream()
            .filter(channel -> equals(channel.getParentCategory()))
            .sorted()
            .toList());
    }

    @NotNull
    default List<NewsChannel> getNewsChannel() {
        return Collections.unmodifiableList(getGuild().getNewsChannels()
            .stream()
            .filter(channel -> equals(channel.getParentCategory()))
            .sorted()
            .toList());
    }

    @NotNull
    default List<StageChannel> getStageChannel() {
        return Collections.unmodifiableList(getGuild().getStageChannels()
            .stream()
            .filter(channel -> equals(channel.getParentCategory()))
            .sorted()
            .toList());
    }


}
