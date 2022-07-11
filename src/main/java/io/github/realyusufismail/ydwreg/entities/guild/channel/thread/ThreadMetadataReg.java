/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.github.realyusufismail.ydwreg.entities.guild.channel.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMetadata;
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
