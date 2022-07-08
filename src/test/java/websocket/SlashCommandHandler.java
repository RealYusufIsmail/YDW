package websocket;

import io.github.realyusufismail.event.adapter.EventAdapter;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydw.event.events.interaction.SlashCommandInteractionEvent;
import io.github.realyusufismail.ydwreg.application.commands.slash.reply.ReplyConfigReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;

import java.awt.*;

public class SlashCommandHandler extends EventAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild().get();
        String name = event.getData().get().getName();
        if (name.equals("roles")) {
            System.out.println("Roles");

            if (event.getChannel().isPresent()) {
                Channel channel = event.getChannel().get();
                event
                    .reply("The channel is " + channel.getName().get(),
                            new ReplyConfigReg().setEphemeral(true))
                    .queue();
            }
        }
    }
}
