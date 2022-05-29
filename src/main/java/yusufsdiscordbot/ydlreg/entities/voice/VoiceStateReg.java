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

package yusufsdiscordbot.ydlreg.entities.voice;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.Guild;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydl.entities.guild.Channel;
import yusufsdiscordbot.ydl.entities.guild.Member;
import yusufsdiscordbot.ydl.entities.voice.VoiceRegion;
import yusufsdiscordbot.ydl.entities.voice.VoiceState;
import yusufsdiscordbot.ydlreg.YDLReg;
import yusufsdiscordbot.ydlreg.entities.guild.MemberReg;
import yusufsdiscordbot.ydlreg.rest.exception.RestApiException;
import yusufsdiscordbot.ydlreg.rest.name.EndPoint;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VoiceStateReg implements VoiceState {
    private final YDL ydl;

    private final Guild guild;
    private final Channel channel;
    private final User user;
    private final Member member;
    private final String sessionId;
    private final Boolean deaf;
    private final Boolean mute;
    private final Boolean selfDeaf;
    private final Boolean selfMute;
    private final Boolean selfStream;
    private final Boolean suppress;
    private final ZonedDateTime requestToSpeakTimeStamp;
    private final List<VoiceRegion> voiceRegions = new ArrayList<VoiceRegion>();

    public VoiceStateReg(JsonNode voice, YDL ydl) {
        this.ydl = ydl;

        this.guild =
                voice.hasNonNull("guild_id") ? ydl.getGuild(voice.get("guild_id").asLong()) : null;
        this.channel =
                voice.hasNonNull("channel_id") ? ydl.getChannel(voice.get("channel_id").asLong())
                        : null;
        this.user = voice.hasNonNull("user_id") ? ydl.getUser(voice.get("user_id").asLong()) : null;
        this.member = voice.hasNonNull("member") ? new MemberReg(voice.get("member"), ydl) : null;
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
        var ydlReg = (YDLReg) ydl;
        try (var response = ydlReg.getHttpClient().newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return new ArrayList<>();
            } else {
                JsonNode json = ydlReg.getMapper().readTree(body.string());
                List<VoiceRegion> voiceRegions = new ArrayList<>();
                for (JsonNode node : json) {
                    voiceRegions.add(new VoiceRegionReg(node, node.get("id").asText(), ydl));
                }
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
        return new ArrayList<>();
    }


    @Override
    public YDL getYDL() {
        return ydl;
    }

    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @Override
    public Optional<Channel> getChannel() {
        return Optional.ofNullable(channel);
    }

    @Override
    public Optional<User> getUser() {
        return Optional.ofNullable(user);
    }

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

    @Override
    public Optional<Boolean> isSelfStream() {
        return Optional.ofNullable(selfStream);
    }

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

    @Override
    public List<VoiceRegion> getVoiceRegions() {
        return voiceRegions;
    }
}
