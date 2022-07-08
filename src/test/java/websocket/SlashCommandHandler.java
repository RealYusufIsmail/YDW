package websocket;

import io.github.realyusufismail.event.adapter.EventAdapter;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.event.events.interaction.SlashCommandInteractionEvent;
import io.github.realyusufismail.ydwreg.application.commands.slash.reply.ReplyConfigReg;

public class SlashCommandHandler extends EventAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String name = event.getData().get().getName();
        if (name.equals("ping")) {
            event
                .reply(event.getYDW().getGatewayPing() + "ms",
                        new ReplyConfigReg().setEphemeral(true))
                .queue(error -> {
                    System.out.println(error.getMessage());
                },

                        success -> {
                            System.out.println("Success");
                        });
        } else if (name.equals("test")) {
            event
                .reply(event.getYDW().getGatewayPing() + "ms",
                        new ReplyConfigReg().setEphemeral(true))
                .queue(error -> {
                    System.out.println(error.getMessage());
                },

                        success -> {
                            System.out.println("Success");
                        });
        }
    }
}
