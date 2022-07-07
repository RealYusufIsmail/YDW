package websocket;

import io.github.realyusufismail.event.adapter.EventAdapter;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydw.event.events.interaction.SlashCommandInteractionEvent;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;

import java.awt.*;

public class SlashCommandHandler extends EventAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild().get();

        if (event.getName().equals("roles")) {
            event.reply("The roles are: " + guild.getRoles()).queue();
        }
    }
}
