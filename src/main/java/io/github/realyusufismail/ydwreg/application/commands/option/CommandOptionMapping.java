package io.github.realyusufismail.ydwreg.application.commands.option;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.application.commands.option.CommandInteractionDataOption;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.channel.NewsChannel;
import io.github.realyusufismail.ydw.entities.channel.TextChannel;
import io.github.realyusufismail.ydw.entities.channel.VoiceChannel;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Role;

import java.util.Optional;

public class CommandOptionMapping implements CommandInteractionDataOption {

    private final JsonNode config;
    private final OptionType type;

    private final String name;
    private final Boolean focused;

    public CommandOptionMapping(JsonNode config, OptionType type) {
        this.config = config;
        this.type = type;
        name = config.get("name").asText();
        focused = config.hasNonNull("focused") ? config.get("focused").asBoolean() : null;
    }

    @Override
    public OptionType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<Boolean> isFocused() {
        return Optional.ofNullable(focused);
    }

    @Override
    public Optional<String> getAsString() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getAsInt() {
        return Optional.empty();
    }

    @Override
    public Optional<Double> getAsDouble() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getAsLong() {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> getAsBoolean() {
        return Optional.empty();
    }

    @Override
    public Member getAsMember() {
        return null;
    }

    @Override
    public User getAsUser() {
        return null;
    }

    @Override
    public Channel getAsChannel() {
        return null;
    }

    @Override
    public TextChannel getAsTextChannel() {
        return null;
    }

    @Override
    public VoiceChannel getAsVoiceChannel() {
        return null;
    }

    @Override
    public NewsChannel getAsNewsChannel() {
        return null;
    }

    @Override
    public Role getAsRole() {
        return null;
    }
}
