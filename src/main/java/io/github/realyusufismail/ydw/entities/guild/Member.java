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
package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface Member extends SnowFlake, GenericEntity {
    Optional<User> getUser();

    Optional<String> getNickname();

    Optional<String> getAvatar();

    @NotNull
    List<Role> getRoles();

    ZonedDateTime getJoinedAt();

    Optional<ZonedDateTime> getPremiumSince();

    Boolean isDeafened();

    Boolean isMuted();

    Optional<Boolean> isPending();

    String getPermissions();

    Optional<ZonedDateTime> getTimeoutEnd();

    Boolean memberHasPermission(Permission permission);
}
