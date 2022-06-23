package io.github.realyusufismail.handler;

import io.github.realyusufismail.ydw.event.BasicEvent;
import org.jetbrains.annotations.NotNull;

/**
 * This is the interface which is used to send events to the bot. <br>
 * <br>
 * For example, if you want to create a slash command, you would use jda.onEvent(SlashCommand.class)
 * and then you add .send so that the event can be run and sent to discord.
 */
public interface EventSender {
    void register(Object consume);

    void unregister(Object consume);

    void send(@NotNull BasicEvent consume);
}
