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
package io.github.realyusufismail.ydw.entities.guild.channel;

import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface NewsChannel extends GuildChannel, SnowFlake {

    @Override
    Optional<Guild> getGuild();

    @Override
    Optional<String> getName();

    @NotNull
    @Override
    default ChannelType getType() {
        return ChannelType.GUILD_NEWS;
    }

    @Override
    Optional<Integer> getPosition();

    @Override
    List<Overwrite> getPermissionOverwrites();

    @Override
    Optional<Boolean> isNSFW();

    @Override
    Optional<String> getTopic();

    @Override
    Optional<SnowFlake> getParentId();

    @Override
    Optional<Integer> getDefaultAutoArchiveDuration();

    @Override
    Optional<Category> getCategory();
}
