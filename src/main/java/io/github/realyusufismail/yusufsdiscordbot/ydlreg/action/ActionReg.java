/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.action;

import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.action.Action;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.RestApiHandler;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ActionReg implements Action {
    private final Request request;

    private final YDL ydl;
    @NotNull
    RestApiHandler api = new RestApiHandler(getYdl());

    public ActionReg(Request request, YDL ydl) {
        this.request = request;
        this.ydl = ydl;
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
    public <T> void queue(Consumer<? super T> success, Consumer<? super Throwable> failure) {
        api.queue(request, success, failure);
    }

    public YDL getYdl() {
        return ydl;
    }
}
