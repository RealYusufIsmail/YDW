package io.github.realyusufismail.ydw.application.commands.reply;

public interface ReplyConfig {

    ReplyConfig setEphemeral(boolean ephemeral);

    ReplyConfig setTTS(boolean tts);

    boolean isEphemeral();

    boolean isTTS();
}
