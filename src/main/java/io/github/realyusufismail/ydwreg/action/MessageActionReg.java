/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
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
