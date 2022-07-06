package websocket;

import io.github.realyusufismail.event.adapter.EventAdapter;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;
import io.github.realyusufismail.ydw.event.events.interaction.SlashCommandInteractionEvent;

import java.util.Objects;

public class handler extends EventAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getData().get().getName().equals("ping")) {
            System.out.println("ping");
            event.reply("pong").queue();
        }
    }
}
