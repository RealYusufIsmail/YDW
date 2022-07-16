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
package io.github.realyusufismail.ydw.entities;

import io.github.realyusufismail.ydwreg.entities.user.PremiumTypes;
import io.github.realyusufismail.ydwreg.entities.user.UserFlags;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface User extends SnowFlake, GenericEntity {
    String getUserName();

    String getDiscriminator();

    String getAvatar();

    Optional<Boolean> isBot();

    Optional<Boolean> isSystem();

    Optional<Boolean> isMFAEnabled();

    Optional<String> getBanner();

    Optional<Integer> getAccentColor();

    Optional<String> getLocale();

    Optional<Boolean> isVerified();

    Optional<String> getEmail();

    @NotNull
    Optional<UserFlags> getFlags();

    @NotNull
    Optional<PremiumTypes> getPremiumType();

    @NotNull
    Optional<UserFlags> getPublicFlags();

    List<Guild> getGuilds();
}
