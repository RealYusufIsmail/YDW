package io.github.realyusufismail.ydw.application.commands.reply;

public interface ReplyConfig {

    boolean isEphemeral();

    ReplyConfig setEphemeral(boolean ephemeral);

    boolean isTTS();

    ReplyConfig setTTS(boolean tts);
}
