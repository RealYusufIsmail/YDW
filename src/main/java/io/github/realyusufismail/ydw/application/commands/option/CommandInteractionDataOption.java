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
package io.github.realyusufismail.ydw.application.commands.option;

import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydw.entities.guild.channel.NewsChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.VoiceChannel;

import java.util.Optional;

public interface CommandInteractionDataOption {

    /**
     * Gets the type of the option.
     *
     * @return the type of the option.
     */
    OptionType getType();


    /**
     * Gets the name of the parameter.
     *
     * @return the name of the parameter.
     */
    String getName();

    /**
     * @return true if this option is the currently focused option for autocomplete
     */
    Optional<Boolean> isFocused();

    Optional<String> getAsString();

    Optional<Integer> getAsInt();

    Optional<Double> getAsDouble();

    Optional<Long> getAsLong();

    Optional<Boolean> getAsBoolean();

    Member getAsMember();

    User getAsUser();

    Channel getAsChannel();

    TextChannel getAsTextChannel();

    VoiceChannel getAsVoiceChannel();

    NewsChannel getAsNewsChannel();

    Role getAsRole();
}
