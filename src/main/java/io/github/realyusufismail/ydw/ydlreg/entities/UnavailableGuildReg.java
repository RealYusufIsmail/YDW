package io.github.realyusufismail.ydw.ydlreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.UnavailableGuild;
import org.jetbrains.annotations.NotNull;

public class UnavailableGuildReg implements UnavailableGuild {

    private final YDL ydl;
    private final long id;

    @NotNull
    private final Boolean isAvailable;

    public UnavailableGuildReg(YDL ydl, long id, @NotNull JsonNode json) {
        this.ydl = ydl;
        this.id = id;

        this.isAvailable = !json.get("unavailable").asBoolean();
    }

    @Override
    public YDL getYDL() {
        return ydl;
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