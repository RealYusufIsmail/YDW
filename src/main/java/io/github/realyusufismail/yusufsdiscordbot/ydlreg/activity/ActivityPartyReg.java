package io.github.realyusufismail.yusufsdiscordbot.ydlreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import io.github.realyusufismail.yusufsdiscordbot.ydl.activity.ActivityParty;

import java.util.HashMap;
import java.util.Map;

public class ActivityPartyReg implements ActivityParty {

    private final String id;
    private final Map<Integer, Integer> size = new HashMap<>();

    public ActivityPartyReg(@NotNull JsonNode party) {
        this.id = party.hasNonNull("id") ? party.get("id").asText() : null;

        if (party.hasNonNull("size")) {
            party.get("size").forEach(sizeNode -> {
                size.put(sizeNode.get("size").asInt(), sizeNode.get("count").asInt());
            });
        }
    }

    @Override
    public Map<Integer, Integer> getSize() {
        return size;
    }

    @Override
    public Long getIdLong() {
        return id != null ? Long.parseLong(id) : null;
    }
}
