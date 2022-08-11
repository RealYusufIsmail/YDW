/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package websocket;

import io.github.realyusufismail.event.adapter.EventAdapter;
import io.github.realyusufismail.ydw.action.ReplyAction;
import io.github.realyusufismail.ydw.entities.SelfUser;
import io.github.realyusufismail.ydw.event.events.interaction.SlashCommandInteractionEvent;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;

import java.awt.*;

public class SlashCommandHandler extends EventAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) {
            // event.replyEmbed(new EmbedBuilder().setTitle("Test").setColor(Color.CYAN).build())
            // .queue();
            event
                .replyEmbed(new EmbedBuilder().setTitle("Ping")
                    .addField("Ping", String.valueOf(event.getYDW().getPing()), false)
                    .setColor(Color.RED)
                    .build(), ReplyAction.config.setEphemeral(true))
                .queue();
        } else if (event.getName().equals("info")) {
            SelfUser selfUser = event.getYDW().getSelfUser();
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle("Info");
            builder.addField("Name", selfUser.getUserName(), false);
            builder.addField("Discriminator", selfUser.getDiscriminator(), false);
            builder.addField("ID", selfUser.getId(), false);
            builder.addField("Avatar", selfUser.getAvatar(), false);

            event.replyEmbed(builder.build())
                .queue(error -> System.out.println("Error: " + error.getCause()));
        }
    }
}
