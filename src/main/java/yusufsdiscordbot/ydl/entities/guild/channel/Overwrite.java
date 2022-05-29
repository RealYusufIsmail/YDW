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

package yusufsdiscordbot.ydl.entities.guild.channel;

import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import org.jetbrains.annotations.Nullable;

public interface Overwrite extends SnowFlake {
    OverwriteType getType();

    String getAllow();

    String getDeny();

    enum OverwriteType {
        ROLE(0),
        MEMBER(1);

        private final int type;

        OverwriteType(int type) {
            this.type = type;
        }

        public static @Nullable OverwriteType getOverwriteType(int type) {
            for (OverwriteType ow : values()) {
                if (ow.getType() == type) {
                    return ow;
                }
            }
            return null;
        }

        public int getType() {
            return type;
        }
    }

}
