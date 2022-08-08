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
package io.github.realyusufismail.ydwreg.action.config;

import io.github.realyusufismail.ydw.action.config.MessageConfig;
import org.jetbrains.annotations.NotNull;

public class MessageConfigReg implements MessageConfig {
    private boolean tts;
    private boolean mentionable;

    public MessageConfigReg() {
        if (this.getClass() != MessageConfigReg.class) {
            throw new IllegalStateException("Cannot instantiate an instance of interface type.");
        }
    }

    @NotNull
    @Override
    public MessageConfig setTTS(boolean tts) {
        this.tts = tts;
        return this;
    }

    @NotNull
    @Override
    public MessageConfig setMentionable(boolean mentionable) {
        this.mentionable = mentionable;
        return this;
    }

    @Override
    public boolean isTTS() {
        return tts;
    }

    @Override
    public boolean isMentionable() {
        return mentionable;
    }
}
