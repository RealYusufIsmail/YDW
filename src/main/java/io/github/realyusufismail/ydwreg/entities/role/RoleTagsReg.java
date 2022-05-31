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

package io.github.realyusufismail.ydwreg.entities.role;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.role.RoleTags;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RoleTagsReg implements RoleTags {
    private final YDW ydw;

    private final Long botId;
    private final Long intergrationId;
    private final Boolean premiumSubscriber;

    public RoleTagsReg(@NotNull JsonNode role, YDW ydw) {
        this.ydw = ydw;

        this.botId = role.hasNonNull("bot_id") ? role.get("bot_id").asLong() : null;
        this.intergrationId =
                role.hasNonNull("integration_id") ? role.get("integration_id").asLong() : null;
        this.premiumSubscriber =
                role.hasNonNull("premium_subscriber") ? role.get("premium_subscriber").asBoolean()
                        : null;
    }

    @Override
    public YDW getYDW() {
        return ydw;
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
