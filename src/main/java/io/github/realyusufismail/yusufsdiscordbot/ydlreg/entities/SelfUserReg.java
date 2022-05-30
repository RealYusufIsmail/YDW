/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.SelfUser;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.User;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.YDLReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;
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

    public SelfUserReg(@NotNull JsonNode user, long userId, @NotNull YDL ydl) {
        super(user, userId, ydl);

        applicationId = userId;
        isMfaEnabled = user.get("mfa_enabled").asBoolean();
        allowedFileSize = user.get("max_file_size").asInt();
        isVerified = user.get("verified").asBoolean();

        SelfUserReg bot =
                (SelfUserReg) (getYDLReg().isSelfUserThere() ? getYDL().getSelfUser() : null);

        if (bot == null) {
            long id = user.get("id").asLong();
            bot = new SelfUserReg(user, id, getYDL());
            getYDLReg().setSelfUser(bot);
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

    public YDLReg getYDLReg() {
        return getYDL();
    }

    @Override
    public void ifPresent(@NotNull Consumer<User> consumer) {
        super.ifPresent(consumer);
    }
}
