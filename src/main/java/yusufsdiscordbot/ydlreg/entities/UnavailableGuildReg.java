package yusufsdiscordbot.ydlreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.UnavailableGuild;

public class UnavailableGuildReg implements UnavailableGuild {

    private final YDL ydl;
    private final long id;

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

    @Override
    public Long getIdLong() {
        return id;
    }
}