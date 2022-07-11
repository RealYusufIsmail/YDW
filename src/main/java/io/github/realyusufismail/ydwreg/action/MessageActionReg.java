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
            
package io.github.realyusufismail.ydwreg.action;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.action.MessageAction;
import io.github.realyusufismail.ydwreg.rest.callers.MessageCaller;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class MessageActionReg implements MessageAction {
    private final Request request;

    private final YDW ydw;

    private final MessageCaller messageCaller;

    public MessageActionReg(Request request, YDW ydw) {
        this.request = request;
        this.ydw = ydw;
        this.messageCaller = ydw.getRest().getMessageCaller();
    }

    @Override
    public void queue(@Nullable Consumer<? super Throwable> failure,
            Consumer<? super Response> success) {
        messageCaller.queue(request, failure, success);
    }

    @Override
    public @NotNull MessageAction isTTs() {
        messageCaller.setTTS(true);
        return this;
    }

    @Override
    public @NotNull MessageAction isMentionable() {
        messageCaller.setMentionable(true);
        return this;
    }

    @Nullable
    public YDW getYDW() {
        return ydw;
    }
}
