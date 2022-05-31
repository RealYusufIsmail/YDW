/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
 */

package io.github.realyusufismail.ydwreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.SelfUser;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public class SelfUserReg extends UserReg implements SelfUser {
    private Long applicationId;
    @NotNull
    private final Boolean isMfaEnabled;
    @NotNull
    private final Integer allowedFileSize;
    @NotNull
    private final Boolean isVerified;

    public SelfUserReg(@NotNull JsonNode user, long userId, @NotNull YDW ydw) {
        super(user, userId, ydw);

        applicationId = userId;
        isMfaEnabled = user.get("mfa_enabled").asBoolean();
        allowedFileSize = user.get("max_file_size").asInt();
        isVerified = user.get("verified").asBoolean();

        SelfUserReg bot =
                (SelfUserReg) (getYDWReg().isSelfUserThere() ? getYDW().getSelfUser() : null);

        if (bot == null) {
            long id = user.get("id").asLong();
            bot = new SelfUserReg(user, id, getYDW());
            getYDWReg().setSelfUser(bot);
        }

        if (!user.get("application_id").isNull())
            this.applicationId = user.get("application_id").asLong();
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
