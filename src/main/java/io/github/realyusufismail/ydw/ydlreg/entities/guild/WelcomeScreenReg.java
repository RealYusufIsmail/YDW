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

package io.github.realyusufismail.ydw.ydlreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.guild.WelcomeScreen;
import io.github.realyusufismail.ydw.ydl.entities.guild.WelcomeScreenChannel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WelcomeScreenReg implements WelcomeScreen {
    private final YDL ydl;

    private final String description;
    private final List<WelcomeScreenChannel> channels = new ArrayList<>();

    public WelcomeScreenReg(@NotNull JsonNode screen, @NotNull YDL ydl, long guildId) {
        this.ydl = ydl;

        this.description =
                screen.hasNonNull("description") ? screen.get("description").asText() : "";

        if (screen.hasNonNull("welcome_channels")) {
            for (JsonNode channel : screen.get("welcome_channels")) {
                channels.add(new WelcomeScreenChannelReg(channel, guildId, ydl));
            }
        }
    }

    @Override
    public YDL getYDL() {
        return ydl;
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
