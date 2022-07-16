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
package io.github.realyusufismail.ydwreg.entities.message;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.entities.guild.message.MessageActivity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MessageActivityReg implements MessageActivity {
    private final MessageActivityType type;
    private final String partyId;

    public MessageActivityReg(@NotNull JsonNode jsom) {
        this.type = MessageActivityType.fromInt(jsom.get("type").asInt());
        this.partyId = jsom.hasNonNull("party_id") ? jsom.get("party_id").asText() : null;
    }

    @Override
    public MessageActivityType getType() {
        return type;
    }

    @Override
    public Optional<String> getPartyId() {
        return Optional.ofNullable(partyId);
    }
}
