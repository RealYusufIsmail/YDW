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

package io.github.realyusufismail.ydw.ydlreg.entities.channel.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydw.ydlreg.util.Verify;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

public class ThreadMetadataReg implements ThreadMetadata {
    private final JsonNode json;
    private final long id;

    private final YDL ydl;

    private boolean isArchived;
    private int autoArchiveDuration;
    private ZonedDateTime autoArchiveTimeStamp;
    private boolean isLocked;

    private boolean isInvitable;
    private ZonedDateTime creationTimestamp;

    public ThreadMetadataReg(JsonNode json, long id, YDL ydl) {
        this.json = json;
        this.id = id;
        this.ydl = ydl;
    }

    @Override
    public boolean isArchived() {
        return isArchived;
    }

    @NotNull
    public ThreadMetadataReg setArchived(boolean archived) {
        isArchived = archived;
        return this;
    }

    @Override
    public int getAutoArchiveDuration() {
        return autoArchiveDuration;
    }

    @NotNull
    public ThreadMetadataReg setAutoArchiveDuration(int autoArchiveDuration) {
        this.autoArchiveDuration = autoArchiveDuration;
        return this;
    }

    @Override
    public ZonedDateTime getArchivedTimestamp() {
        return autoArchiveTimeStamp;
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @NotNull
    public ThreadMetadataReg setLocked(boolean locked) {
        isLocked = locked;
        return this;
    }

    @Override
    public boolean isInvitable() {
        return isInvitable;
    }

    @NotNull
    public ThreadMetadataReg setInvitable(boolean invitable) {
        isInvitable = invitable;
        return this;
    }

    @Override
    public ZonedDateTime getCreationTimestamp() {
        Verify.checkIfNull(creationTimestamp, "creation_timestamp");
        return creationTimestamp;
    }

    @NotNull
    public ThreadMetadataReg setCreationTimestamp(ZonedDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
        return this;
    }

    @NotNull
    public ThreadMetadataReg setAutoArchiveTimeStamp(ZonedDateTime autoArchiveTimeStamp) {
        this.autoArchiveTimeStamp = autoArchiveTimeStamp;
        return this;
    }
}
