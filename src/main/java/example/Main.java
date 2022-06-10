package example;

import io.github.realyusufismail.websocket.event.events.interaction.SlashCommandInteractionEvent;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.YDWConnector;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {
        YDW ydw = YDWConnector.setUpBot("").setGuildId("").build();

        ydw.newSlashCommand("ping", "replays with pong");

        ydw.onEvent(SlashCommandInteractionEvent.class).subscribe(event -> {

            Embed embedBuilder = new EmbedBuilder().setTitle("Pong").setColor(Color.CYAN).build();

            event.replyEmbed(embedBuilder).queue();
        });
    }
}
