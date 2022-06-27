package io.github.realyusufismail.ydw.entities.channel;

import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface GroupDm extends Channel {

    Optional<String> getName();

    Optional<Long> lastMessageId();

    @NotNull
    @Override
    default ChannelType getType() {
        return ChannelType.GROUP_DM;
    }

    List<User> getUsers();

    @Override
    Optional<User> getOwner();
}
