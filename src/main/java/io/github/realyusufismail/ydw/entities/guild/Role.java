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
            
package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.guild.role.RoleTags;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Optional;

public interface Role extends SnowFlake, GenericEntity {
    int DEFAULT_COLOR_RAW = Color.darkGray.getRGB();

    String getName();

    Integer getColour();

    Boolean isHoisted();

    Optional<String> getIcon();

    Optional<String> getUnicodeEmoji();

    Integer getPosition();

    @NotNull
    Permission[] getPermissions();

    Boolean isManaged();

    Boolean isMentionable();

    @NotNull
    Optional<RoleTags> getRoleTags();
}
