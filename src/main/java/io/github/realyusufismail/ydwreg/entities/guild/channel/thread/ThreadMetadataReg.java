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
package io.github.realyusufismail.ydwreg.entities.guild.channel.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydwreg.util.Verify;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.Optional;

public class ThreadMetadataReg implements ThreadMetadata {

    private boolean isArchived;
    private int autoArchiveDuration;
    private ZonedDateTime autoArchiveTimeStamp;
    private boolean isLocked;

    private boolean isInvitable;
    private ZonedDateTime creationTimestamp;

    public ThreadMetadataReg(JsonNode json) {
        isArchived = json.get("archived").asBoolean();
        autoArchiveDuration = json.get("auto_archive_duration").asInt();
        autoArchiveTimeStamp = json.hasNonNull("archive_timestamp")
                ? ZonedDateTime.parse(json.get("archive_timestamp").asText())
                : null;
        isLocked = json.get("locked").asBoolean();
        isInvitable = json.get("invitable").asBoolean();
        creationTimestamp = ZonedDateTime.parse(json.get("creation_timestamp").asText());
    }

    @Override
    public boolean isArchived() {
        return isArchived;
    }

    @Override
    public int getAutoArchiveDuration() {
        return autoArchiveDuration;
    }


    @Override
    public Optional<ZonedDateTime> getArchivedTimestamp() {
        return Optional.ofNullable(autoArchiveTimeStamp);
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public boolean isInvitable() {
        return isInvitable;
    }

    @Override
    public ZonedDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    // setters
    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public void setAutoArchiveDuration(int autoArchiveDuration) {
        this.autoArchiveDuration = autoArchiveDuration;
    }

    public void setAutoArchiveTimeStamp(ZonedDateTime autoArchiveTimeStamp) {
        this.autoArchiveTimeStamp = autoArchiveTimeStamp;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setInvitable(boolean invitable) {
        isInvitable = invitable;
    }

    public void setCreationTimestamp(ZonedDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
