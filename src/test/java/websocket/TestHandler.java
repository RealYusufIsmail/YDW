package websocket;

import io.github.realyusufismail.handler.EventHandlerAdapter;
import io.github.realyusufismail.websocket.event.events.ReadyEvent;
import io.github.realyusufismail.websocket.event.events.interaction.SlashCommandInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestHandler extends EventHandlerAdapter {
    // logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onReadyEvent(ReadyEvent event) {
        logger.info("Guild count: {}", event.getNumberOfAvailableGuilds());
    }

    @Override
    public void onSlashCommandInteractionEvent(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) {
            event.reply("pong").isEphemeral().queue();
        }
    }
}
