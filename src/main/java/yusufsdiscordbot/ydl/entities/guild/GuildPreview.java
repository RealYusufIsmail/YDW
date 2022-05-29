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

package yusufsdiscordbot.ydl.entities.guild;

import api.ydl.snowflake.SnowFlake;
import yusufsdiscordbot.ydl.entities.GenericEntity;
import yusufsdiscordbot.ydl.entities.emoji.Emoji;
import yusufsdiscordbot.ydl.entities.sticker.Sticker;
import yusufsdiscordbot.ydlreg.entities.guild.GuildFeatures;

import java.util.EnumSet;
import java.util.List;

public interface GuildPreview extends SnowFlake, GenericEntity {

    String getName();

    String getIcon();

    String getSplash();

    String getDiscoverySplash();

    List<Emoji> getEmojis();

    EnumSet<GuildFeatures> getFeatures();

    Integer getApproximateMemberCount();

    Integer getApproximatePresenceCount();

    String getDescription();

    List<Sticker> getStickers();
}
