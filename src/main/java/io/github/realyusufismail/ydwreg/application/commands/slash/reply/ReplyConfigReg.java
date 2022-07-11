/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
            
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
