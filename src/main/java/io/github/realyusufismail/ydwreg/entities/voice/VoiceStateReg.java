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

package io.github.realyusufismail.ydwreg.entities.voice;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.voice.VoiceRegion;
import io.github.realyusufismail.ydw.entities.voice.VoiceState;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydwreg.rest.exception.RestApiException;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VoiceStateReg implements VoiceState {
    private final YDW ydw;

    private final Guild guild;
    private final Channel channel;
    @Nullable
    private final User user;
    @Nullable
    private final Member member;
    private final String sessionId;
    @NotNull
    private final Boolean deaf;
    @NotNull
    private final Boolean mute;
    @NotNull
    private final Boolean selfDeaf;
    @NotNull
    private final Boolean selfMute;
    private final Boolean selfStream;
    @NotNull
    private final Boolean suppress;
    private final ZonedDateTime requestToSpeakTimeStamp;
    private final List<VoiceRegion> voiceRegions = new ArrayList<VoiceRegion>();

    public VoiceStateReg(@NotNull JsonNode voice, @NotNull YDW ydw) {
        this.ydw = ydw;

        this.guild =
                voice.hasNonNull("guild_id") ? ydw.getGuild(voice.get("guild_id").asLong()) : null;
        this.channel =
                voice.hasNonNull("channel_id") ? ydw.getChannel(voice.get("channel_id").asLong())
                        : null;
        this.user = voice.hasNonNull("user_id") ? ydw.getUser(voice.get("user_id").asLong()) : null;
        this.member = voice.hasNonNull("member") ? new MemberReg(voice.get("member"), ydw) : null;
        this.sessionId = voice.get("session_id").asText();
        this.deaf = voice.get("deaf").asBoolean();
        this.mute = voice.get("mute").asBoolean();
        this.selfDeaf = voice.get("self_deaf").asBoolean();
        this.selfMute = voice.get("self_mute").asBoolean();
        this.selfStream =
                voice.hasNonNull("self_stream") ? voice.get("self_stream").asBoolean() : null;
        this.suppress = voice.get("suppress").asBoolean();
        this.requestToSpeakTimeStamp =
                ZonedDateTime.parse(voice.get("request_to_speak_time").asText());
        this.voiceRegions.addAll(Objects.requireNonNull(getRegion()));
    }

    private @NotNull List<VoiceRegion> getRegion() {
        Request request =
                new Request.Builder().url(EndPoint.LIST_VOICE_REGIONS.getEndpoint()).get().build();
        var ydwReg = (YDWReg) ydw;
        try (var response = ydwReg.getHttpClient().newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return new ArrayList<>();
            } else {
                JsonNode json = ydwReg.getMapper().readTree(body.string());
                List<VoiceRegion> voiceRegions = new ArrayList<>();
                for (JsonNode node : json) {
                    voiceRegions.add(new VoiceRegionReg(node, node.get("id").asText(), ydw));
                }
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
        return new ArrayList<>();
    }


    @Override
    public YDW getYDW() {
        return ydw;
    }

    @NotNull
    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @NotNull
    @Override
    public Optional<Channel> getChannel() {
        return Optional.ofNullable(channel);
    }

    @NotNull
    @Override
    public Optional<User> getUser() {
        return Optional.ofNullable(user);
    }

    @NotNull
    @Override
    public Optional<Member> getMember() {
        return Optional.ofNullable(member);
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public Boolean isDeafened() {
        return deaf;
    }

    @Override
    public Boolean isMuted() {
        return mute;
    }

    @Override
    public Boolean isSelfDeafened() {
        return selfDeaf;
    }

    @Override
    public Boolean isSelfMuted() {
        return selfMute;
    }

    @NotNull
    @Override
    public Optional<Boolean> isSelfStream() {
        return Optional.ofNullable(selfStream);
    }

    @NotNull
    @Override
    public Boolean isSelfVideo() {
        return !suppress;
    }

    @Override
    public Boolean isSuppressed() {
        return suppress;
    }

    @Override
    public ZonedDateTime getRequestToSpeakTimeStamp() {
        return requestToSpeakTimeStamp;
    }

    @NotNull
    @Override
    public List<VoiceRegion> getVoiceRegions() {
        return voiceRegions;
    }
}
