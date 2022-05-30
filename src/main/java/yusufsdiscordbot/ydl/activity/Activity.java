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

package yusufsdiscordbot.ydl.activity;

import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.application.interaction.InteractionData;
import yusufsdiscordbot.ydl.entities.GenericEntity;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydl.entities.emoji.Emoji;
import yusufsdiscordbot.ydl.entities.guild.Member;
import yusufsdiscordbot.ydl.entities.guild.Message;
import yusufsdiscordbot.ydlreg.application.interaction.InteractionType;
import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import java.time.ZonedDateTime;
import java.util.Optional;

public interface Activity extends SnowFlake, GenericEntity {

    /**
     * Gets the activity name.
     * @return The name of the activity.
     */
    String getName();

    /**
     * Gets the activity type.
     * @return The activity type.
     */
    ActivityConfig getActivityType();

    /**
     * Gets the stream url, is validated when type is 1.
     * @return The stream url, is validated when type is 1.
     */
    Optional<String> getUrl();

    /**
     * Gets the unix timestamp (in milliseconds) of when the activity was added to the user's session.
     * @return The unix timestamp (in milliseconds) of when the activity was added to the user's session.
     */
    ZonedDateTime getCreatedAt();

    /**
     * Gets the unix timestamps for start and/or end of the game.
     * @return The unix timestamps for start and/or end of the game.
     */
    Optional<ActivityTimeStamp> getTimeStamp();

    /**
     * Gets the application id of the game.
     * @return The application id of the game.
     */
    Optional<SnowFlake> getApplicationId();

    /**
     * Gets the details of the activity.
     * @return The details of the activity.
     */
    Optional<String> getDetails();

    /**
     * Gets the state of the activity.
     * @return The state of the activity.
     */
    Optional<String> getState();

    /**
     * If an emoji is used in the activity, this will return the emoji.
     * @return If an emoji is used in the activity, this will return the emoji.
     */
    Optional<ActivityEmoji> getEmoji();

    /**
     * Gets information about the players party.
     * @return Information about the players party.
     */
    Optional<ActivityParty> getParty();


}
