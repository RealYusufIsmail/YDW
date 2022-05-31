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

package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.WelcomeScreen;
import io.github.realyusufismail.ydw.entities.guild.WelcomeScreenChannel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WelcomeScreenReg implements WelcomeScreen {
    private final YDW ydw;

    private final String description;
    private final List<WelcomeScreenChannel> channels = new ArrayList<>();

    public WelcomeScreenReg(@NotNull JsonNode screen, @NotNull YDW ydw, long guildId) {
        this.ydw = ydw;

        this.description =
                screen.hasNonNull("description") ? screen.get("description").asText() : "";

        if (screen.hasNonNull("welcome_channels")) {
            for (JsonNode channel : screen.get("welcome_channels")) {
                channels.add(new WelcomeScreenChannelReg(channel, guildId, ydw));
            }
        }
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @NotNull
    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @NotNull
    @Override
    public List<WelcomeScreenChannel> getWelcomeChannels() {
        return channels;
    }
}
