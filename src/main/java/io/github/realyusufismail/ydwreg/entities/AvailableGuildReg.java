package io.github.realyusufismail.ydwreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.AvailableGuild;
import io.github.realyusufismail.ydw.entities.UnavailableGuild;
import org.jetbrains.annotations.NotNull;

public class AvailableGuildReg implements AvailableGuild {

    private final YDW ydw;
    private final long id;

    @NotNull
    private final Boolean isAvailable;

    public AvailableGuildReg(YDW ydw, long id, @NotNull JsonNode json) {
        this.ydw = ydw;
        this.id = id;

        this.isAvailable = !json.get("unavailable").asBoolean();
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @Override
    public Boolean isAvailable() {
        return isAvailable;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}

