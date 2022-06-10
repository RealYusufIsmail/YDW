package io.github.realyusufismail.ydwreg.application.commands.slash.config;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.action.ReplyAction;
import io.github.realyusufismail.ydw.application.commands.reply.IReply;
import io.github.realyusufismail.ydw.application.commands.slash.SlashCommand;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydwreg.application.commands.ApplicationCommandReg;
import org.jetbrains.annotations.NotNull;

public class SlashCommandConfig extends ApplicationCommandReg implements SlashCommand, IReply {

    public SlashCommandConfig(@NotNull JsonNode application, long id, YDW ydw) {
        super(application, id, ydw);
    }

    @Override
    public ReplyAction reply(String message) {
        return null;
    }

    @Override
    public ReplyAction replyEmbed(Embed embed) {
        return null;
    }

    @Override
    public Member getMember() {
        return null;
    }

    @Override
    public User getUser() {
        return null;
    }
}
