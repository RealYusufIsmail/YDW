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
import io.github.realyusufismail.ydw.action.Action;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.rest.queue.Queue;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ActionReg implements Action {
    private final Request request;

    private final YDWReg ydw;

    private final OkHttpClient client;

    public ActionReg(Request request, YDW ydw) {
        this.request = request;
        this.ydw = (YDWReg) ydw;
        this.client = this.ydw.getHttpClient();
    }

    @Override
    public void queue() {
        queue(null);
    }

    @Override
    public void queue(@Nullable Consumer<? super Throwable> failure) {
        new Queue(client, request, failure).queue();
    }

    @Nullable
    public YDW getYDW() {
        return ydw;
    }
}
