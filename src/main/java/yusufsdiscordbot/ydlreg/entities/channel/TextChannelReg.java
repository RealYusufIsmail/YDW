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

package yusufsdiscordbot.ydlreg.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.action.Action;
import yusufsdiscordbot.ydl.action.MessageAction;
import yusufsdiscordbot.ydl.entities.guild.GuildChannel;
import yusufsdiscordbot.ydl.entities.guild.Message;
import yusufsdiscordbot.ydl.entities.guild.channel.TextChannel;
import yusufsdiscordbot.ydlreg.YDLReg;
import yusufsdiscordbot.ydlreg.entities.GuildReg;
import yusufsdiscordbot.ydlreg.entities.embed.builder.EmbedBuilder;
import yusufsdiscordbot.ydlreg.entities.guild.ChannelReg;
import yusufsdiscordbot.ydlreg.rest.callers.MessageCaller;

// TODO : once rest api is done start creating the rest api for this
public class TextChannelReg extends ChannelReg implements TextChannel {
    private final YDL ydl;
    @NotNull
    MessageCaller apiHandler = getYDL().getRest().getMessageRestApi();

    public TextChannelReg(JsonNode json, long id, YDL ydl) {
        super(json, id, ydl);
        this.ydl = ydl;
    }

    @Override
    public @NotNull MessageAction sendMessage(String message) {
        return null;
    }

    @Override
    public @NotNull MessageAction sendEmbedMessage(@NotNull EmbedBuilder embedBuilder) {
        // apiHandler.sendMessage(getId(), embedBuilder.build());
        // return new MessageActionReg();
        return null;
    }


    @Override
    public @NotNull Message getMessage(@NotNull String messageId) {
        // return apiHandler.getMessage(getId(), messageId);
        return null;
    }

    @Override
    public @NotNull Message getMessage(long messageId) {
        // return apiHandler.getMessage(getId(), messageId);
        return null;
    }

    @Override
    public @Nullable Action deleteMessage(@NotNull String messageId) {
        return null;
    }

    @Override
    public @Nullable Action deleteMessage(long messageId) {
        return null;
    }


    @Override
    public @Nullable Action deleteMessages(int min, int max) {
        return null;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(getIdLong(), o.getIdLong());
    }

    @Override
    public YDLReg getYDL() {
        return (YDLReg) ydl;
    }

    public TextChannel build(JsonNode object, long guildId) {
        return build(null, object, guildId);
    }

    public TextChannel build(GuildReg guildReg, @NotNull JsonNode object, long guildId) {
        boolean playbackCache = false;
        final long id = object.get("id").asLong();
        TextChannelReg channelReg = (TextChannelReg) getYDL().getTextChannelCacheReg().get(id);
        if (channelReg == null) {
            if (guildReg == null)
                guildReg = (GuildReg) getYDL().getGuildCacheReg().get(guildId);
            SnowFlakeCacheRetrieverReg<TextChannel> channelRetriever =
                    guildReg.getTextChannelsReg(),
                    textRetriever = getYDL().getTextChannelCacheReg();

            try (HookLock guildLock = channelRetriever.writeLock();
                    HookLock textLock = textRetriever.writeLock()) {
                channelReg = new TextChannelReg(id, getYDL());
                channelRetriever.getMap().put(id, channelReg);
                playbackCache = textRetriever.getMap().put(id, channelReg) == null;
            }
        }

        channelReg.setParentId(object.get("parent_id").asLong())
            .setLastMessageId(object.get("last_message_id").asLong())
            .setName(object.get("name").asText())
            .setTopic(object.get("topic").asText())
            .setPosition(object.get("position").asInt())
            .setNsfw(object.get("nsfw").asBoolean());
        return channelReg;
    }
}
