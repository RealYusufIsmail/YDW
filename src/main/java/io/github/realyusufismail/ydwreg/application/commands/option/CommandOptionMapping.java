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
            
package io.github.realyusufismail.ydwreg.application.commands.option;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.application.commands.option.CommandInteractionDataOption;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydw.entities.guild.channel.NewsChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.VoiceChannel;

import java.util.Optional;

public class CommandOptionMapping implements CommandInteractionDataOption {

    private final JsonNode config;
    private final OptionType type;

    private final String name;
    private final Boolean focused;

    public CommandOptionMapping(JsonNode config, OptionType type) {
        this.config = config;
        this.type = type;
        name = config.get("name").asText();
        focused = config.hasNonNull("focused") ? config.get("focused").asBoolean() : null;
    }

    @Override
    public OptionType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<Boolean> isFocused() {
        return Optional.ofNullable(focused);
    }

    @Override
    public Optional<String> getAsString() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getAsInt() {
        return Optional.empty();
    }

    @Override
    public Optional<Double> getAsDouble() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getAsLong() {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> getAsBoolean() {
        return Optional.empty();
    }

    @Override
    public Member getAsMember() {
        return null;
    }

    @Override
    public User getAsUser() {
        return null;
    }

    @Override
    public Channel getAsChannel() {
        return null;
    }

    @Override
    public TextChannel getAsTextChannel() {
        return null;
    }

    @Override
    public VoiceChannel getAsVoiceChannel() {
        return null;
    }

    @Override
    public NewsChannel getAsNewsChannel() {
        return null;
    }

    @Override
    public Role getAsRole() {
        return null;
    }
}
