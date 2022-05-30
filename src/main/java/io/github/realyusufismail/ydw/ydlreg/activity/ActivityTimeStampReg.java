package io.github.realyusufismail.ydw.ydlreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.activity.ActivityTimeStamp;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ActivityTimeStampReg implements ActivityTimeStamp {

    private final Integer start;
    private final Integer end;

    public ActivityTimeStampReg(@NotNull JsonNode timeStamp) {
        this.start = timeStamp.hasNonNull("start") ? timeStamp.get("start").asInt() : null;
        this.end = timeStamp.hasNonNull("end") ? timeStamp.get("end").asInt() : null;
    }


    @NotNull
    @Override
    public Optional<Integer> getStart() {
        return Optional.ofNullable(start);
    }

    @NotNull
    @Override
    public Optional<Integer> getEnd() {
        return Optional.ofNullable(end);
    }
}
