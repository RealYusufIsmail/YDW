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

package yusufsdiscordbot.ydlreg.action.reg;

import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydlreg.action.ReplyAction;
import yusufsdiscordbot.ydlreg.rest.RestApiHandler;

import java.util.function.Consumer;

public class ReplyActionReg implements ReplyAction {
    private final Request request;

    private final YDL ydl;
    @NotNull
    RestApiHandler api = new RestApiHandler(getYdl());

    public ReplyActionReg(Request request, YDL ydl) {
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

    @Override
    public @NotNull ReplyAction isEphemeral() {
        api.setEphemeral(true);
        return this;
    }

    @Override
    public @NotNull ReplyAction isTTS() {
        api.setTTS(true);
        return this;
    }

    public YDL getYdl() {
        return ydl;
    }
}
