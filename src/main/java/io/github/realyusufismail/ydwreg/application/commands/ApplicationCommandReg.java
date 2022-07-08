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

package io.github.realyusufismail.ydwreg.application.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.ApplicationCommand;
import io.github.realyusufismail.ydw.application.commands.option.CommandOption;
import io.github.realyusufismail.ydw.application.commands.option.CommandType;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydwreg.application.commands.option.CommandOptionReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationCommandReg implements ApplicationCommand {

    private final long id;
    private final YDW ydw;

    private final CommandType type;
    private final long applicationId;
    private final Guild guild;
    private final String name;
    private final String description;
    private final String[] defaultPermission;
    private final List<CommandOption> options = new ArrayList<>();
    private final Boolean dmVisible;
    private final Long version;

    public ApplicationCommandReg(@NotNull JsonNode application, long id, YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.type = application.hasNonNull("type")
                ? CommandType.getCommandType(application.get("type").asInt())
                : null;
        this.applicationId = application.get("application_id").asLong();
        this.guild = application.hasNonNull("guild_id")
                ? ydw.getGuild(application.get("guild_id").asLong())
                : null;
        this.name = application.get("name").asText();
        this.description = application.get("description").asText();
        this.defaultPermission = application.hasNonNull("default_permission")
                ? application.get("default_permission").asText().split(",")
                : null;
        this.dmVisible = application.hasNonNull("dm_permission")
                ? application.get("dm_permission").asBoolean()
                : null;
        this.version = application.get("version").asLong();

        if (application.hasNonNull("options") && application.get("options").isArray()) {
            application.get("options").forEach(option -> {
                CommandOptionReg commandOption = new CommandOptionReg(option);
                options.add(commandOption);
            });
        }
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public Optional<CommandType> getCommandType() {
        return Optional.ofNullable(type);
    }

    @NotNull
    @Override
    public SnowFlake getApplicationIdLong() {
        return SnowFlake.of(applicationId);
    }

    @NotNull
    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Optional<String[]> getDefaultMemberPermissions() {
        return Optional.ofNullable(defaultPermission);
    }

    @Override
    public boolean isDmVisible() {
        return dmVisible;
    }

    @Override
    public List<CommandOption> getOptions() {
        return options;
    }

    @NotNull
    @Override
    public SnowFlake getVersion() {
        return SnowFlake.of(version);
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("id", id);
        getCommandType().ifPresent(commandType -> objectNode.put("type", commandType.getValue()));
        objectNode.put("application_id", applicationId);
        getGuild().ifPresent(guild -> objectNode.put("guild_id", guild.getIdLong()));
        objectNode.put("name", name);
        objectNode.put("description", description);
        getDefaultMemberPermissions().ifPresent(
                permissions -> objectNode.put("default_permission", String.join(",", permissions)));
        objectNode.put("dm_permission", dmVisible);
        objectNode.put("version", version);
        return objectNode;
    }

    @Nullable
    @Override
    public YDW getYDW() {
        return ydw;
    }
}
