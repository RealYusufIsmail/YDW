/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.github.realyusufismail.ydwreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.activity.*;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class ActivityReg implements Activity {

    private final YDW ydw;
    private final long id;

    private final String name;
    @NotNull
    private final ActivityConfig type;
    private final String url;
    @NotNull
    private final Integer createdAt;
    @Nullable
    private final ActivityTimeStamp timeStamp;
    private final Long applicationId;
    private final String detail;
    private final String state;
    @Nullable
    private final ActivityEmoji emoji;
    @Nullable
    private final ActivityParty party;
    @Nullable
    private final ActivityAsset assets;
    @Nullable
    private final ActivitySecret secrets;
    private final Boolean instance;
    private final EnumSet<ActivityFlag> flags = EnumSet.noneOf(ActivityFlag.class);
    private final List<ActivityButton> buttons = new ArrayList<>();


    public ActivityReg(@NotNull JsonNode activity, long id, YDW ydw) {
        this.ydw = ydw;
        this.id = id;

        this.name = activity.get("name").asText();
        this.type = ActivityConfig.getActivity(activity.get("type").asInt());
        this.url = activity.hasNonNull("url") ? activity.get("url").asText() : null;
        this.createdAt = activity.get("created_at").asInt();
        this.timeStamp = activity.hasNonNull("timestamps")
                ? new ActivityTimeStampReg(activity.get("timestamps"))
                : null;
        this.applicationId =
                activity.hasNonNull("application_id") ? activity.get("application_id").asLong()
                        : null;
        this.detail = activity.hasNonNull("details") ? activity.get("details").asText() : null;
        this.state = activity.hasNonNull("state") ? activity.get("state").asText() : null;
        this.emoji =
                activity.hasNonNull("emoji")
                        ? new ActivityEmojiReg(activity.get("emoji"),
                                activity.get("emoji").get("id").asLong())
                        : null;
        this.party =
                activity.hasNonNull("party") ? new ActivityPartyReg(activity.get("party")) : null;
        this.assets =
                activity.hasNonNull("assets") ? new ActivityAssetReg(activity.get("assets")) : null;
        this.secrets =
                activity.hasNonNull("secrets") ? new ActivitySecretReg(activity.get("secrets"))
                        : null;
        this.instance =
                activity.hasNonNull("instance") ? activity.get("instance").asBoolean() : null;

        if (activity.hasNonNull("flags")) {
            for (JsonNode flag : activity.get("flags")) {
                this.flags.add(ActivityFlag.getFlag(flag.asInt()));
            }
        }

        if (activity.hasNonNull("buttons")) {
            for (JsonNode button : activity.get("buttons")) {
                this.buttons.add(new ActivityButtonReg(button));
            }
        }
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public ActivityConfig getActivityType() {
        return type;
    }

    @NotNull
    @Override
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    @NotNull
    @Override
    public ZonedDateTime getCreatedAt() {
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(createdAt), ZoneId.systemDefault());
    }

    @NotNull
    @Override
    public Optional<ActivityTimeStamp> getTimeStamp() {
        return Optional.ofNullable(timeStamp);
    }

    @NotNull
    @Override
    public Optional<SnowFlake> getApplicationId() {
        return Optional.ofNullable(applicationId).map(SnowFlake::of);
    }

    @NotNull
    @Override
    public Optional<String> getDetails() {
        return Optional.ofNullable(detail);
    }

    @NotNull
    @Override
    public Optional<String> getState() {
        return Optional.ofNullable(state);
    }

    @NotNull
    @Override
    public Optional<ActivityEmoji> getEmoji() {
        return Optional.ofNullable(emoji);
    }

    @NotNull
    @Override
    public Optional<ActivityParty> getParty() {
        return Optional.ofNullable(party);
    }

    @NotNull
    @Override
    public Optional<ActivityAsset> getAssets() {
        return Optional.ofNullable(assets);
    }

    @NotNull
    @Override
    public Optional<Boolean> isInstance() {
        return Optional.ofNullable(instance);
    }

    @NotNull
    @Override
    public EnumSet<ActivityFlag> getFlags() {
        return flags;
    }

    @NotNull
    @Override
    public List<ActivityButton> getButtons() {
        return buttons;
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
