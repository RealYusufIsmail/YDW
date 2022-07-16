/*
 *
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *
 * distributed under the License is distributed on an "AS IS" BASIS,
 *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 *
 * limitations under the License.
 *
 *
 */

package io.github.realyusufismail.ydwreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Mentions;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MentionsReg implements Mentions {

    private final List<User> users = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    public MentionsReg(@NotNull JsonNode json, YDW ydw) {

        for (JsonNode user : json.get("users")) {
            users.add(new UserReg(user, user.get("id").asLong(), ydw));
        }

        for (JsonNode member : json.get("members")) {
            members.add(new MemberReg(member, ydw));
        }
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public List<Member> getMembers() {
        return members;
    }
}
