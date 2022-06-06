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

package io.github.realyusufismail.ydwreg.entities.channel.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydwreg.util.Verify;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

public class ThreadMetadataReg implements ThreadMetadata {
    private final JsonNode json;
    private final long id;

    private final YDW ydw;

    private boolean isArchived;
    private int autoArchiveDuration;
    private ZonedDateTime autoArchiveTimeStamp;
    private boolean isLocked;

    private boolean isInvitable;
    private ZonedDateTime creationTimestamp;

    public ThreadMetadataReg(JsonNode json, long id, YDW ydw) {
        this.json = json;
        this.id = id;
        this.ydw = ydw;
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
