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

import java.util.List;

public class Payload {
    private Op op;
    private Op s;
    private T t;
    private D d;

    public Op getOp() {
        return op;
    }

    public void setOp(Op value) {
        this.op = value;
    }

    public Op getS() {
        return s;
    }

    public void setS(Op value) {
        this.s = value;
    }

    public T getT() {
        return t;
    }

    public void setT(T value) {
        this.t = value;
    }

    public D getD() {
        return d;
    }

    public void setD(D value) {
        this.d = value;
    }

    public static class D {
        private UserSettings userSettings;
        private List<Object> relationships;
        private List<T> trace;
        private List<Object> presences;
        private Application application;
        private List<Guild> guilds;
        private Op v;
        private List<Object> guildJoinRequests;
        private List<T> geoOrderedrtcRegions;
        private T sessionid;
        private List<Object> privateChannels;
        private User user;

        public UserSettings getUserSettings() {
            return userSettings;
        }

        public void setUserSettings(UserSettings value) {
            this.userSettings = value;
        }

        public List<Object> getRelationships() {
            return relationships;
        }

        public void setRelationships(List<Object> value) {
            this.relationships = value;
        }

        public List<T> getTrace() {
            return trace;
        }

        public void setTrace(List<T> value) {
            this.trace = value;
        }

        public List<Object> getPresences() {
            return presences;
        }

        public void setPresences(List<Object> value) {
            this.presences = value;
        }

        public Application getApplication() {
            return application;
        }

        public void setApplication(Application value) {
            this.application = value;
        }

        public List<Guild> getGuilds() {
            return guilds;
        }

        public void setGuilds(List<Guild> value) {
            this.guilds = value;
        }

        public Op getV() {
            return v;
        }

        public void setV(Op value) {
            this.v = value;
        }

        public List<Object> getGuildJoinRequests() {
            return guildJoinRequests;
        }

        public void setGuildJoinRequests(List<Object> value) {
            this.guildJoinRequests = value;
        }

        public List<T> getGeoOrderedrtcRegions() {
            return geoOrderedrtcRegions;
        }

        public void setGeoOrderedrtcRegions(List<T> value) {
            this.geoOrderedrtcRegions = value;
        }

        public T getSessionid() {
            return sessionid;
        }

        public void setSessionid(T value) {
            this.sessionid = value;
        }

        public List<Object> getPrivateChannels() {
            return privateChannels;
        }

        public void setPrivateChannels(List<Object> value) {
            this.privateChannels = value;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User value) {
            this.user = value;
        }
    }

    public static class Application {
        private Op flags;
        private T id;

        public Op getFlags() {
            return flags;
        }

        public void setFlags(Op value) {
            this.flags = value;
        }

        public T getid() {
            return id;
        }

        public void setid(T value) {
            this.id = value;
        }
    }

    public static class Op {
        private long num;

        public long getNum() {
            return num;
        }

        public void setNum(long value) {
            this.num = value;
        }
    }

    public static class T {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class Guild {
        private UserSettings unavailable;
        private T id;

        public UserSettings getUnavailable() {
            return unavailable;
        }

        public void setUnavailable(UserSettings value) {
            this.unavailable = value;
        }

        public T getid() {
            return id;
        }

        public void setid(T value) {
            this.id = value;
        }
    }

    public static class UserSettings {
    }

    public static class User {
        private UserSettings bot;
        private UserSettings verified;
        private Op flags;
        private UserSettings mfaEnabled;
        private T id;
        private UserSettings avatar;
        private UserSettings email;
        private T username;
        private T discriminator;

        public UserSettings getBot() {
            return bot;
        }

        public void setBot(UserSettings value) {
            this.bot = value;
        }

        public UserSettings getVerified() {
            return verified;
        }

        public void setVerified(UserSettings value) {
            this.verified = value;
        }

        public Op getFlags() {
            return flags;
        }

        public void setFlags(Op value) {
            this.flags = value;
        }

        public UserSettings getMfaEnabled() {
            return mfaEnabled;
        }

        public void setMfaEnabled(UserSettings value) {
            this.mfaEnabled = value;
        }

        public T getid() {
            return id;
        }

        public void setid(T value) {
            this.id = value;
        }

        public UserSettings getAvatar() {
            return avatar;
        }

        public void setAvatar(UserSettings value) {
            this.avatar = value;
        }

        public UserSettings getEmail() {
            return email;
        }

        public void setEmail(UserSettings value) {
            this.email = value;
        }

        public T getUsername() {
            return username;
        }

        public void setUsername(T value) {
            this.username = value;
        }

        public T getDiscriminator() {
            return discriminator;
        }

        public void setDiscriminator(T value) {
            this.discriminator = value;
        }
    }
}
