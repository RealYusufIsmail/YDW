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

package yusufsdiscordbot.ydlreg.entities.activity;

import api.ydl.snowflake.SnowFlake;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.application.interaction.InteractionData;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydl.entities.activity.Activity;
import yusufsdiscordbot.ydl.entities.guild.Member;
import yusufsdiscordbot.ydl.entities.guild.Message;
import yusufsdiscordbot.ydlreg.application.interaction.InteractionDataReg;
import yusufsdiscordbot.ydlreg.application.interaction.InteractionType;
import yusufsdiscordbot.ydlreg.entities.UserReg;
import yusufsdiscordbot.ydlreg.entities.guild.MemberReg;
import yusufsdiscordbot.ydlreg.entities.guild.MessageReg;

public class ActivityReg implements Activity {
    private final JsonNode activity;
    private final YDL ydl;

    public ActivityReg(JsonNode activity, YDL ydl) {
        this.activity = activity;
        this.ydl = ydl;
    }

    @Override
    public @NotNull SnowFlake getApplicationId() {
        return SnowFlake.of(activity.get("application_id").asText());
    }

    @Override
    public InteractionType getInteractionType() {
        return InteractionType.fromValue(activity.get("type").asInt());
    }

    @Override
    public @NotNull InteractionData getInteractionData() {
        return new InteractionDataReg(activity.getAsJsonNode("data"));
    }

    @Override
    public @NotNull SnowFlake getGuildId() {
        return SnowFlake.of(activity.get("guild_id").asText());
    }

    @Override
    public @NotNull SnowFlake getChannelId() {
        return SnowFlake.of(activity.get("channel_id").asText());
    }

    @Override
    public @NotNull Member getMember() {
        return new MemberReg(activity.getAsJsonNode("member"), ydl);
    }

    @Override
    public @NotNull User getUser() {
        return new UserReg(activity.getAsJsonNode("user"), ydl);
    }

    @Override
    public String getToken() {
        return activity.get("token").asText();
    }

    @Override
    public int getVersion() {
        return activity.get("version").asInt();
    }

    @Override
    public @NotNull Message getMessage() {
        return new MessageReg(activity.getAsJsonNode("message"), ydl);
    }

    @Override
    public String getLocale() {
        return activity.get("locale").asText();
    }

    @Override
    public String getGuildLocale() {
        return activity.get("guild_locale").asText();
    }

    @Override
    public Long getIdLong() {
        return activity.get("id").asLong();
    }

    @Override
    public YDL getYDL() {
        return ydl;
    }
}
