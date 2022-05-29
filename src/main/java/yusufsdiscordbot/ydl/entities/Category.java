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

package yusufsdiscordbot.ydl.entities;

import yusufsdiscordbot.ydl.entities.guild.GuildChannel;
import yusufsdiscordbot.ydl.entities.guild.channel.NewsChannel;
import yusufsdiscordbot.ydl.entities.guild.channel.StageChannel;
import yusufsdiscordbot.ydl.entities.guild.channel.TextChannel;
import yusufsdiscordbot.ydl.entities.guild.channel.VoiceChannel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Category extends GuildChannel {
    default List<GuildChannel> getChannels() {
        List<GuildChannel> channels = new ArrayList<>();
        channels.addAll(getTextChannel());
        channels.addAll(getVoiceChannel());
        channels.addAll(getNewsChannel());
        channels.addAll(getStageChannel());
        Collections.sort(channels);
        return channels;
    }

    default List<TextChannel> getTextChannel() {
        return Collections.unmodifiableList(getGuild().getTextChannels()
            .stream()
            .filter(channel -> equals(channel.getParentCategory()))
            .sorted()
            .toList());
    }

    default List<VoiceChannel> getVoiceChannel() {
        return Collections.unmodifiableList(getGuild().getVoiceChannels()
            .stream()
            .filter(channel -> equals(channel.getParentCategory()))
            .sorted()
            .toList());
    }

    default List<NewsChannel> getNewsChannel() {
        return Collections.unmodifiableList(getGuild().getNewsChannels()
            .stream()
            .filter(channel -> equals(channel.getParentCategory()))
            .sorted()
            .toList());
    }

    default List<StageChannel> getStageChannel() {
        return Collections.unmodifiableList(getGuild().getStageChannels()
            .stream()
            .filter(channel -> equals(channel.getParentCategory()))
            .sorted()
            .toList());
    }


}
