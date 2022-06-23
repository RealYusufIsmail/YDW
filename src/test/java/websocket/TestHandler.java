package websocket;

import io.github.realyusufismail.handler.EventHandlerAdapter;
import io.github.realyusufismail.websocket.event.events.ReadyEvent;
import io.github.realyusufismail.websocket.event.events.interaction.SlashCommandInteractionEvent;

public class TestHandler extends EventHandlerAdapter {

    @Override
    public void onReadyEvent(ReadyEvent event) {
        System.out.println("Ready!");
    }

    @Override
    public void onSlashCommandInteractionEvent(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) {
            event.reply("pong").queue();
        }
    }
}
