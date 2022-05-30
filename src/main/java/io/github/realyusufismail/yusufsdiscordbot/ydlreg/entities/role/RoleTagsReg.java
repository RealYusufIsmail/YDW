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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.role;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.role.RoleTags;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RoleTagsReg implements RoleTags {
    private final YDL ydl;

    private final Long botId;
    private final Long intergrationId;
    private final Boolean premiumSubscriber;

    public RoleTagsReg(@NotNull JsonNode role, YDL ydl) {
        this.ydl = ydl;

        this.botId = role.hasNonNull("bot_id") ? role.get("bot_id").asLong() : null;
        this.intergrationId =
                role.hasNonNull("integration_id") ? role.get("integration_id").asLong() : null;
        this.premiumSubscriber =
                role.hasNonNull("premium_subscriber") ? role.get("premium_subscriber").asBoolean()
                        : null;
    }

    @Override
    public YDL getYDL() {
        return ydl;
    }

    @NotNull
    @Override
    public Optional<SnowFlake> getBotId() {
        return Optional.ofNullable(botId).map(SnowFlake::of);
    }

    @NotNull
    @Override
    public Optional<SnowFlake> getIntegrationId() {
        return Optional.ofNullable(intergrationId).map(SnowFlake::of);
    }

    @NotNull
    @Override
    public Optional<Boolean> isPremiumSubscriber() {
        return Optional.ofNullable(premiumSubscriber);
    }
}
