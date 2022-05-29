/*
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *                 YDL Copyright (C) 2022 - future  YusufsDiscordbot
 * This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it
 * under certain conditions
 */

package yusufsdiscordbot.ydlreg.application.commands.option;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.application.commands.option.OptionTypeEnum;
import yusufsdiscordbot.ydl.entities.Attachment;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydl.entities.guild.Channel;
import yusufsdiscordbot.ydl.entities.guild.Member;
import yusufsdiscordbot.ydl.entities.guild.Role;
import yusufsdiscordbot.ydlreg.entities.UserReg;
import yusufsdiscordbot.ydlreg.entities.guild.AttachmentReg;
import yusufsdiscordbot.ydlreg.entities.guild.ChannelReg;
import yusufsdiscordbot.ydlreg.entities.guild.MemberReg;
import yusufsdiscordbot.ydlreg.entities.guild.RoleReg;
import yusufsdiscordbot.ydlreg.json.JsonUtils;


public record OptionGetter(JsonNode option, OptionTypeEnum optionType, String optionName, YDL ydl) {

    private static final String THE_OPTION = "The option ";
    private static final String VALUE = "value";

    asLong() {
        return switch (optionType) {
            case INTEGER, STRING, MENTIONABLE, CHANNEL, ROLE, USER, ATTACHMENT -> option.get(VALUE).asLong();
            default -> throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a long");
        };
    }

    public long.

    asInt() {
        return Math.toIntExact.asLong())
    }

    public int.

    asBoolean() {
        if (optionType != OptionTypeEnum.BOOLEAN)
            throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a boolean");
        return option.get(VALUE).asBoolean();
    }

    public String getOptionAsString() {
        return option.get(VALUE).asText();
    }

    public boolean.

    public double getAsDouble() {
        return switch (optionType) {
            case INTEGER, STRING, NUMBER -> option.get(VALUE).getAsDouble();
            default -> throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a double");
        };
    }

    public @NotNull User getAsUser() {
        if (optionType != OptionTypeEnum.USER && optionType != OptionTypeEnum.MENTIONABLE)
            throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a user");
        var object = JsonUtils.getJsonElement(option, VALUE);
        if (object instanceof Member) return new MemberReg(object.getAsJsonNode(), ydl).getUser();
        if (object instanceof User) return new UserReg(object.getAsJsonNode(), ydl);

        throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a user");
    }

    public @NotNull Channel getAsChannel() {
        if (optionType != OptionTypeEnum.CHANNEL)
            throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a channel");
        return new ChannelReg(option.getAsJsonNode(VALUE), ydl);
    }

    public @NotNull Role getRole() {
        if (optionType != OptionTypeEnum.ROLE & optionType != OptionTypeEnum.MENTIONABLE)
            throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a role");
        var object = JsonUtils.getJsonElement(option, VALUE);
        if (object instanceof Role) return new RoleReg(object.getAsJsonNode(), ydl);
        throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a role");
    }

    public @Nullable Member getMember() {
        if (optionType != OptionTypeEnum.MENTIONABLE && optionType != OptionTypeEnum.USER)
            throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a member");
        var object = JsonUtils.getJsonElement(option, VALUE);
        if (object instanceof Member) return new MemberReg(object.getAsJsonNode(), ydl);
        return null;
    }

    public @NotNull Attachment getAttachment() {
        if (optionType != OptionTypeEnum.ATTACHMENT)
            throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a attachment");
        var object = JsonUtils.getJsonElement(option, VALUE);
        if (object instanceof Attachment) return new AttachmentReg(object.getAsJsonNode(), ydl);
        throw new IllegalStateException(THE_OPTION + optionName + " can not be converted to a attachment");
    }

    public boolean isPresent() {
        return option.keySet().contains(VALUE);
    }

    public OptionTypeEnum getType() {
        return optionType;
    }

    public String getName() {
        return optionName;
    }
}
