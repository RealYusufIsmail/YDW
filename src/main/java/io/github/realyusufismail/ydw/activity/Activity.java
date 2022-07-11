/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
            
package io.github.realyusufismail.ydw.activity;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

import java.time.ZonedDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public interface Activity extends SnowFlake, GenericEntity {

    /**
     * Gets the activity name.
     *
     * @return The name of the activity.
     */
    String getName();

    /**
     * Gets the activity type.
     *
     * @return The activity type.
     */
    ActivityConfig getActivityType();

    /**
     * Gets the stream url, is validated when type is 1.
     *
     * @return The stream url, is validated when type is 1.
     */
    Optional<String> getUrl();

    /**
     * Gets the unix timestamp (in milliseconds) of when the activity was added to the user's
     * session.
     *
     * @return The unix timestamp (in milliseconds) of when the activity was added to the user's
     *         session.
     */
    ZonedDateTime getCreatedAt();

    /**
     * Gets the unix timestamps for start and/or end of the game.
     *
     * @return The unix timestamps for start and/or end of the game.
     */
    Optional<ActivityTimeStamp> getTimeStamp();

    /**
     * Gets the application id of the game.
     *
     * @return The application id of the game.
     */
    Optional<SnowFlake> getApplicationId();

    /**
     * Gets the details of the activity.
     *
     * @return The details of the activity.
     */
    Optional<String> getDetails();

    /**
     * Gets the state of the activity.
     *
     * @return The state of the activity.
     */
    Optional<String> getState();

    /**
     * If an emoji is used in the activity, this will return the emoji.
     *
     * @return If an emoji is used in the activity, this will return the emoji.
     */
    Optional<ActivityEmoji> getEmoji();

    /**
     * Gets information about the players party.
     *
     * @return Information about the players party.
     */
    Optional<ActivityParty> getParty();

    /**
     * Gets the image and text used in the activity.
     *
     * @return The image and text used in the activity.
     */
    Optional<ActivityAsset> getAssets();

    /**
     * Weather or not the activity is an instanced game session
     *
     * @return true if the activity is an instanced game session, false otherwise
     */
    Optional<Boolean> isInstance();

    /**
     * Gets the flags of the activity.
     *
     * @return The flags of the activity.
     */
    EnumSet<ActivityFlag> getFlags();

    /**
     * Gets the custom buttons shown in the activity. (max 2)
     *
     * @return The custom button shown in the activity. (max 2)
     */
    List<ActivityButton> getButtons();
}
