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
package io.github.realyusufismail.websocket.json;

import java.time.OffsetDateTime;
import java.util.List;

public class Interaction {
    private long op;
    private long s;
    private String t;
    private D d;

    public long getOp() {
        return op;
    }

    public void setOp(long value) {
        this.op = value;
    }

    public long getS() {
        return s;
    }

    public void setS(long value) {
        this.s = value;
    }

    public String getT() {
        return t;
    }

    public void setT(String value) {
        this.t = value;
    }

    public D getD() {
        return d;
    }

    public void setD(D value) {
        this.d = value;
    }

    public static class D {
        private Data data;
        private Member member;
        private String guildid;
        private String id;
        private long type;
        private String locale;
        private long version;
        private String channelid;
        private String applicationid;
        private String token;
        private String guildLocale;

        public Data getData() {
            return data;
        }

        public void setData(Data value) {
            this.data = value;
        }

        public Member getMember() {
            return member;
        }

        public void setMember(Member value) {
            this.member = value;
        }

        public String getGuildid() {
            return guildid;
        }

        public void setGuildid(String value) {
            this.guildid = value;
        }

        public String getid() {
            return id;
        }

        public void setid(String value) {
            this.id = value;
        }

        public long getType() {
            return type;
        }

        public void setType(long value) {
            this.type = value;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String value) {
            this.locale = value;
        }

        public long getVersion() {
            return version;
        }

        public void setVersion(long value) {
            this.version = value;
        }

        public String getChannelid() {
            return channelid;
        }

        public void setChannelid(String value) {
            this.channelid = value;
        }

        public String getApplicationid() {
            return applicationid;
        }

        public void setApplicationid(String value) {
            this.applicationid = value;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String value) {
            this.token = value;
        }

        public String getGuildLocale() {
            return guildLocale;
        }

        public void setGuildLocale(String value) {
            this.guildLocale = value;
        }
    }

    public static class Data {
        private String name;
        private String guildid;
        private String id;
        private long type;

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }

        public String getGuildid() {
            return guildid;
        }

        public void setGuildid(String value) {
            this.guildid = value;
        }

        public String getid() {
            return id;
        }

        public void setid(String value) {
            this.id = value;
        }

        public long getType() {
            return type;
        }

        public void setType(long value) {
            this.type = value;
        }
    }

    public static class Member {
        private List<String> roles;
        private boolean pending;
        private long flags;
        private boolean deaf;
        private boolean mute;
        private OffsetDateTime joinedAt;
        private String permissions;
        private User user;
        private boolean isPending;

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> value) {
            this.roles = value;
        }

        public boolean getPending() {
            return pending;
        }

        public void setPending(boolean value) {
            this.pending = value;
        }

        public long getFlags() {
            return flags;
        }

        public void setFlags(long value) {
            this.flags = value;
        }

        public boolean getDeaf() {
            return deaf;
        }

        public void setDeaf(boolean value) {
            this.deaf = value;
        }

        public boolean getMute() {
            return mute;
        }

        public void setMute(boolean value) {
            this.mute = value;
        }

        public OffsetDateTime getJoinedAt() {
            return joinedAt;
        }

        public void setJoinedAt(OffsetDateTime value) {
            this.joinedAt = value;
        }

        public String getPermissions() {
            return permissions;
        }

        public void setPermissions(String value) {
            this.permissions = value;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User value) {
            this.user = value;
        }

        public boolean getIsPending() {
            return isPending;
        }

        public void setIsPending(boolean value) {
            this.isPending = value;
        }
    }

    public static class User {
        private long publicFlags;
        private String id;
        private String avatar;
        private String username;
        private String discriminator;

        public long getPublicFlags() {
            return publicFlags;
        }

        public void setPublicFlags(long value) {
            this.publicFlags = value;
        }

        public String getid() {
            return id;
        }

        public void setid(String value) {
            this.id = value;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String value) {
            this.avatar = value;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String value) {
            this.username = value;
        }

        public String getDiscriminator() {
            return discriminator;
        }

        public void setDiscriminator(String value) {
            this.discriminator = value;
        }
    }
}
