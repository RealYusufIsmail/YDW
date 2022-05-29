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

package yusufsdiscordbot.ydlreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.SelfUser;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydlreg.YDLReg;
import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import java.util.Optional;
import java.util.function.Consumer;

public class SelfUserReg extends UserReg implements SelfUser {
    private Long applicationId;
    private final Boolean isMfaEnabled;
    private final Integer allowedFileSize;
    private final Boolean isVerified;

    public SelfUserReg(JsonNode user, long userId, YDL ydl) {
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

    @Override
    public SnowFlake getApplicationId() {
        return SnowFlake.of(applicationId);
    }

    @Override
    public Optional<Boolean> isVerified() {
        return Optional.ofNullable(isVerified);
    }

    @Override
    public Optional<Boolean> isMfaEnabled() {
        return Optional.ofNullable(isMfaEnabled);
    }

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
