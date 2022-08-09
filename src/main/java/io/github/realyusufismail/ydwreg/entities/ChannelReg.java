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
package io.github.realyusufismail.ydwreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.action.MessageActionReg;
import io.github.realyusufismail.ydwreg.entities.channel.OverwriteReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.thread.ThreadMetadataReg;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChannelReg implements Channel {
    protected final long id;

    protected final YDW ydw;

    // Channel Structure
    @NotNull
    protected final ChannelType type;


    public ChannelReg(@NotNull JsonNode channelJ, long id, @NotNull YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.type = ChannelType.getChannelType(channelJ.get("type").asInt());
    }

    /**
     * @return The core long of this api.
     */
    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public YDWReg getYDW() {
        return (YDWReg) ydw;
    }

    @Override
    public @NotNull ChannelType getType() {
        return type;
    }
}
