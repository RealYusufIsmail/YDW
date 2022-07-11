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
            
package io.github.realyusufismail.ydw.application;

import io.github.realyusufismail.ydw.application.interaction.InteractionData;
import io.github.realyusufismail.ydw.application.interaction.InteractionType;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface Interaction extends SnowFlake, GenericEntity {

    @Nullable
    SnowFlake getApplicationId();

    @Nullable
    InteractionType getType();

    Optional<InteractionData> getData();

    Optional<Guild> getGuild();

    Optional<Channel> getChannel();

    Optional<Member> getMember();

    Optional<User> getUser();

    String getToken();

    Integer getVersion();

    Message getMessage();

    Optional<String> getLocale();

    Optional<String> getGuildLocale();
}
