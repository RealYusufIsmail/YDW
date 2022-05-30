package yusufsdiscordbot.ydlreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.activity.ActivitySecret;

import java.util.Optional;

public class ActivitySecretReg implements ActivitySecret {

    private final String join;
    private final String spectate;
    private final String match;

    public ActivitySecretReg(@NotNull JsonNode secret) {
        this.join = secret.hasNonNull("join") ? secret.get("join").asText() : null;
        this.spectate = secret.hasNonNull("spectate") ? secret.get("spectate").asText() : null;
        this.match = secret.hasNonNull("match") ? secret.get("match").asText() : null;
    }

    @Override
    public Optional<String> getJoin() {
        return Optional.ofNullable(join);
    }

    @Override
    public Optional<String> getSpectate() {
        return Optional.ofNullable(spectate);
    }

    @Override
    public Optional<String> getMatch() {
        return Optional.ofNullable(match);
    }
}
