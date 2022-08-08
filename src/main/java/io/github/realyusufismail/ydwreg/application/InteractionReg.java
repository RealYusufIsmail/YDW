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
package io.github.realyusufismail.ydwreg.application;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.Interaction;
import io.github.realyusufismail.ydw.application.interaction.InteractionData;
import io.github.realyusufismail.ydw.application.interaction.InteractionType;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.event.Event;
import io.github.realyusufismail.ydwreg.application.commands.option.interaction.InteractionDataReg;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydwreg.entities.guild.MessageReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class InteractionReg extends Event implements Interaction {

    protected final YDW ydw;
    private final long id;
    private final Long applicationId;
    private final InteractionType type;
    private final InteractionData data;
    private final Guild guild;
    private final Channel channel;
    private final Member member;
    private final User user;
    private final String token;
    private final Integer version;
    private final Message message;
    private final String locale;
    private final String guildLocale;

    public InteractionReg(@NotNull JsonNode interaction, long id, YDW ydw) {
        super(ydw);
        this.id = id;
        this.ydw = ydw;

        applicationId = interaction.get("application_id").asLong();
        type = InteractionType.getValue(interaction.get("type").asInt());
        data = interaction.hasNonNull("data")
                ? new InteractionDataReg(interaction.get("data"),
                        interaction.get("data").get("id").asLong(), ydw)
                : null;
        guild = interaction.hasNonNull("guild_id")
                ? ydw.getGuild(interaction.get("guild_id").asLong())
                : null;
        channel = interaction.hasNonNull("channel_id")
                ? ydw.getChannel(interaction.get("channel_id").asLong())
                : null;
        member = interaction.hasNonNull("member") ? new MemberReg(interaction.get("member"), ydw)
                : null;
        user = interaction.hasNonNull("user")
                ? new UserReg(interaction.get("user"), interaction.get("user").get("id").asLong(),
                        ydw)
                : null;
        token = interaction.get("token").asText();
        version = interaction.get("version").asInt();
        message =
                interaction.hasNonNull("message")
                        ? new MessageReg(interaction.get("message"),
                                interaction.get("message").get("id").asLong(), ydw)
                        : null;
        locale = interaction.hasNonNull("locale") ? interaction.get("locale").asText() : null;
        guildLocale =
                interaction.hasNonNull("guild_locale") ? interaction.get("guild_locale").asText()
                        : null;
    }

    @Nullable
    @Override
    public SnowFlake getApplicationId() {
        return SnowFlake.of(applicationId);
    }

    @Nullable
    @Override
    public InteractionType getType() {
        return type;
    }

    @Override
    public Optional<InteractionData> getData() {
        return Optional.ofNullable(data);
    }

    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @Override
    public Optional<Channel> getChannel() {
        return Optional.ofNullable(channel);
    }

    @Override
    public Optional<Member> getMember() {
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<User> getUser() {
        return Optional.ofNullable(user);
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public Message getMessage() {
        return message;
    }

    @Override
    public Optional<String> getLocale() {
        return Optional.ofNullable(locale);
    }

    @Override
    public Optional<String> getGuildLocale() {
        return Optional.ofNullable(guildLocale);
    }

    @Nullable
    @Override
    public YDW getYDW() {
        return ydw;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
