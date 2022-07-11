/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.github.realyusufismail.ydwreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.entities.SelfUser;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public class SelfUserReg extends UserReg implements SelfUser {
    private final Long applicationId;
    private final Boolean isMfaEnabled;
    private final Integer allowedFileSize;
    private final Boolean isVerified;

    public SelfUserReg(@NotNull JsonNode user, long userId, @NotNull YDWReg ydw) {
        super(user, userId, ydw);

        applicationId = userId;
        isMfaEnabled = user.get("mfa_enabled").asBoolean();
        allowedFileSize =
                user.hasNonNull("allowed_file_size") ? user.get("allowed_file_size").asInt() : null;
        isVerified = user.get("verified").asBoolean();
    }

    @NotNull
    @Override
    public SnowFlake getApplicationId() {
        return SnowFlake.of(applicationId);
    }

    @NotNull
    @Override
    public Optional<Boolean> isVerified() {
        return Optional.ofNullable(isVerified);
    }

    @NotNull
    @Override
    public Optional<Boolean> isMfaEnabled() {
        return Optional.ofNullable(isMfaEnabled);
    }

    @NotNull
    @Override
    public Optional<Integer> getAllowedFileSize() {
        return Optional.ofNullable(allowedFileSize);
    }

    public YDWReg getYDWReg() {
        return getYDW();
    }

    @Override
    public void ifPresent(@NotNull Consumer<User> consumer) {
        super.ifPresent(consumer);
    }
}
