package io.github.realyusufismail.ydwreg.application.commands.perm;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.application.commands.perm.ApplicationCommandPermission;
import io.github.realyusufismail.ydw.application.commands.perm.ApplicationCommandPermissionType;
import org.jetbrains.annotations.NotNull;

public class ApplicationCommandPermissionReg implements ApplicationCommandPermission {
    private final long id;

    private final int type;
    private final boolean permission;

    public ApplicationCommandPermissionReg(JsonNode json, long id) {
        this.id = id;

        this.type = json.get("type").asInt();
        this.permission = json.get("permission").asBoolean();
    }

    @Override
    public ApplicationCommandPermissionType getType() {
        return ApplicationCommandPermissionType.fromValue(type);
    }

    @Override
    public boolean hasPermission() {
        return permission;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
