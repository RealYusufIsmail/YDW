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
package io.github.realyusufismail.ydw.application.interaction.resolved;

import io.github.realyusufismail.ydw.entities.Attachment;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface ResolvedData {
    /**
     * @return the ids and User objects
     */
    Map<SnowFlake, User> getUsers();

    /**
     * @return the ids and partial Member objects
     */
    @Nullable
    Map<SnowFlake, Member> getMembers();

    /**
     * @return the ids and Role objects
     */
    @Nullable
    Map<SnowFlake, Role> getRoles();

    /**
     * @return the ids and partial Channel objects
     */
    @Nullable
    Map<SnowFlake, Channel> getChannels();

    /**
     * @return the ids and partial Message objects
     */
    @Nullable
    Map<SnowFlake, Message> getMessages();

    /**
     * @return the ids and attachment objects
     */
    @Nullable
    Map<SnowFlake, Attachment> getAttachments();
}
