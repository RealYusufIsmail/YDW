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

package io.github.realyusufismail.ydwreg.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.channel.Dm;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DmReg extends ChannelReg implements Dm {

    private final Long lassMessageId;
    private final User user;

    public DmReg(@NotNull JsonNode channelJ, long id, @NotNull YDW ydw) {
        super(channelJ, id, ydw);

        this.lassMessageId =
                channelJ.hasNonNull("last_message_id") ? channelJ.get("last_message_id").asLong()
                        : null;
        this.user = channelJ.hasNonNull("recipients")
                ? ydw.getUser(channelJ.get("recipients").get(0).get("id").asLong())
                : null;
    }

    @Override
    public Optional<SnowFlake> getLastMessageId() {
        return Optional.ofNullable(lassMessageId).map(SnowFlake::of);
    }

    @Override
    public User getUser() {
        return user;
    }
}
