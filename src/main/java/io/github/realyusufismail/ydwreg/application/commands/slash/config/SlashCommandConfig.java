package io.github.realyusufismail.ydwreg.application.commands.slash.config;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.action.ReplyAction;
import io.github.realyusufismail.ydw.application.commands.reply.IReply;
import io.github.realyusufismail.ydw.application.commands.slash.SlashCommand;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydwreg.application.commands.ApplicationCommandReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlashCommandConfig extends ApplicationCommandReg implements SlashCommand, IReply {

    public SlashCommandConfig(@NotNull JsonNode application, long id, YDW ydw) {
        super(application, id, ydw);
    }

    @Nullable
    @Override
    public ReplyAction reply(String message) {
        return null;
    }

    @Nullable
    @Override
    public ReplyAction replyEmbed(EmbedBuilder embed) {
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
