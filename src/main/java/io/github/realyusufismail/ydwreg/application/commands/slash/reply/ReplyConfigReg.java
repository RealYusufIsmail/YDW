package io.github.realyusufismail.ydwreg.application.commands.slash.reply;

import io.github.realyusufismail.ydw.application.commands.reply.ReplyConfig;

public class ReplyConfigReg implements ReplyConfig {

    private boolean ephemeral;
    private boolean tts;


    @Override
    public ReplyConfig setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
        return this;
    }

    @Override
    public ReplyConfig setTTS(boolean tts) {
        this.tts = tts;
        return this;
    }

    @Override
    public boolean isEphemeral() {
        return ephemeral;
    }

    @Override
    public boolean isTTS() {
        return tts;
    }
}
