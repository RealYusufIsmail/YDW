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

package io.github.realyusufismail.ydw.ydlreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.activity.*;
import io.github.realyusufismail.ydw.ydlreg.snowflake.SnowFlake;
import io.github.realyusufismail.yusufsdiscordbot.ydl.activity.*;
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

    private final YDL ydl;
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


    public ActivityReg(@NotNull JsonNode activity, long id, YDL ydl) {
        this.ydl = ydl;
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
    public YDL getYDL() {
        return ydl;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
