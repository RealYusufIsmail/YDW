package io.github.realyusufismail.ydwreg.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.GroupDm;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupDmReg extends ChannelReg implements GroupDm {

    private final String name;
    private final Long lastMessageId;
    private final List<User> users = new ArrayList<>();
    private final User owner;

    public GroupDmReg(@NotNull JsonNode channelJ, long id, @NotNull YDW ydw) {
        super(channelJ, id, ydw);

        this.name = channelJ.hasNonNull("name") ? channelJ.get("name").asText() : null;
        this.lastMessageId =
                channelJ.hasNonNull("last_message_id") ? channelJ.get("last_message_id").asLong()
                        : null;
        this.owner =
                channelJ.hasNonNull("owner_id") ? ydw.getUser(channelJ.get("owner_id").asLong())
                        : null;

        if (channelJ.hasNonNull("recipients")) {
            channelJ.get("recipients").forEach(recipient -> {
                this.users.add(ydw.getUser(recipient.asLong()));
            });
        }
    }

    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @Override
    public Optional<Long> lastMessageId() {
        return Optional.ofNullable(lastMessageId);
    }

    @NotNull
    @Override
    public ChannelType getType() {
        return GroupDm.super.getType();
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public Optional<User> getOwner() {
        return Optional.ofNullable(owner);
    }
}
