package io.github.realyusufismail.yusufsdiscordbot.ydlreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import io.github.realyusufismail.yusufsdiscordbot.ydl.activity.ActivityTimeStamp;

import java.util.Optional;

public class ActivityTimeStampReg implements ActivityTimeStamp {

    private final Integer start;
    private final Integer end;

    public ActivityTimeStampReg(@NotNull JsonNode timeStamp) {
        this.start = timeStamp.hasNonNull("start") ? timeStamp.get("start").asInt() : null;
        this.end = timeStamp.hasNonNull("end") ? timeStamp.get("end").asInt() : null;
    }


    @Override
    public Optional<Integer> getStart() {
        return Optional.ofNullable(start);
    }

    @Override
    public Optional<Integer> getEnd() {
        return Optional.ofNullable(end);
    }
}
