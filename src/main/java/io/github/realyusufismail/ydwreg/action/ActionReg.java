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
import io.github.realyusufismail.ydwreg.rest.RestApiHandler;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ActionReg implements Action {
    private final Request request;

    private final YDW ydw;
    @NotNull
    RestApiHandler api = new RestApiHandler(getYDW());

    public ActionReg(Request request, YDW ydw) {
        this.request = request;
        this.ydw = ydw;
    }

    @Override
    public void queue() {
        queue(null, null);
    }

    @Override
    public <T> void queue(@NotNull Consumer<? super T> success) {
        queue(success, null);
    }

    @Override
    public <T> void queue(@Nullable Consumer<? super T> success,
            @Nullable Consumer<? super Throwable> failure) {
        api.queue(request, success, failure);
    }

    @Nullable
    public YDW getYDW() {
        return ydw;
    }
}
