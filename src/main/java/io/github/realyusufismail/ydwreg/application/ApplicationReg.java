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
            
package io.github.realyusufismail.ydwreg.application;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.Application;
import io.github.realyusufismail.ydw.application.flags.ApplicationFlag;
import io.github.realyusufismail.ydw.application.install.InstallParams;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.team.Team;
import io.github.realyusufismail.ydwreg.application.install.InstallParamsReg;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import io.github.realyusufismail.ydwreg.team.TeamReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationReg implements Application {
    private final YDW ydw;
    private final long id;

    private final String name;
    private final String description;
    private final String icon;
    private final List<String> rpcRegions = new ArrayList<>();
    @NotNull
    private final Boolean isPublic;
    @NotNull
    private final Boolean isRequireCodeGrant;
    private final String termsOfServiceUrl;
    private final String privacyPolicyUrl;
    @Nullable
    private final User owner;
    private final String verifyKey;
    @Nullable
    private final Team team;
    private final Guild guild;
    private final Long primarySkuId;
    private final String slug;
    private final String coverImage;
    private final ApplicationFlag[] flags;
    private final String tags;
    @Nullable
    private final InstallParams install;
    private final String installUrl;

    public ApplicationReg(@NotNull JsonNode application, long id, @NotNull YDW ydw) {
        this.ydw = ydw;
        this.id = id;

        this.name = application.get("name").asText();
        this.description = application.get("description").asText();
        this.icon = application.get("icon").asText();
        this.isPublic = application.get("is_public").asBoolean();
        this.isRequireCodeGrant = application.get("is_require_code_grant").asBoolean();
        this.termsOfServiceUrl = application.hasNonNull("terms_of_service_url")
                ? application.get("terms_of_service_url").asText()
                : null;
        this.privacyPolicyUrl = application.hasNonNull("privacy_policy_url")
                ? application.get("privacy_policy_url").asText()
                : null;
        this.owner =
                application.hasNonNull("owner")
                        ? new UserReg(application.get("owner"),
                                application.get("owner").get("id").asLong(), ydw)
                        : null;
        this.verifyKey = application.get("verify_key").asText();
        this.team =
                application.hasNonNull("team")
                        ? new TeamReg(application.get("team"),
                                application.get("team").get("id").asLong(), ydw)
                        : null;
        this.guild = application.hasNonNull("guild_id")
                ? ydw.getGuild(application.get("guild_id").asLong())
                : null;
        this.primarySkuId = application.hasNonNull("primary_sku_id")
                ? application.get("primary_sku_id").asLong()
                : null;
        this.slug = application.hasNonNull("slug") ? application.get("slug").asText() : null;
        this.coverImage =
                application.hasNonNull("cover_image") ? application.get("cover_image").asText()
                        : null;
        this.flags = application.hasNonNull("flags")
                ? ApplicationFlag.fromValues(application.get("flags").asInt())
                : null;
        this.tags = application.hasNonNull("tags") ? application.get("tags").asText() : null;
        this.install =
                application.hasNonNull("install") ? new InstallParamsReg(application.get("install"))
                        : null;
        this.installUrl =
                application.hasNonNull("install_url") ? application.get("install_url").asText()
                        : null;
        if (application.hasNonNull("rpc_regions")) {
            application.get("rpc_regions").forEach(rpcRegion -> rpcRegions.add(rpcRegion.asText()));
        }
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @NotNull
    @Override
    public List<String> getRPCRegions() {
        return rpcRegions;
    }

    @Override
    public Boolean isPublic() {
        return isPublic;
    }

    @Override
    public Boolean isRequireCodeGrant() {
        return isRequireCodeGrant;
    }

    @NotNull
    @Override
    public Optional<String> getTermsOfServiceUrl() {
        return Optional.ofNullable(termsOfServiceUrl);
    }

    @NotNull
    @Override
    public Optional<String> getPrivacyPolicyUrl() {
        return Optional.ofNullable(privacyPolicyUrl);
    }

    @NotNull
    @Override
    public Optional<User> getOwner() {
        return Optional.ofNullable(owner);
    }

    @Override
    public String getVerifyKey() {
        return verifyKey;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @NotNull
    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @NotNull
    @Override
    public Optional<SnowFlake> getPrimarySkuId() {
        return Optional.ofNullable(primarySkuId).map(SnowFlake::of);
    }

    @NotNull
    @Override
    public Optional<String> getSlug() {
        return Optional.ofNullable(slug);
    }

    @NotNull
    @Override
    public Optional<String> getCoverImage() {
        return Optional.ofNullable(coverImage);
    }

    @NotNull
    @Override
    public Optional<ApplicationFlag[]> getFlags() {
        return Optional.ofNullable(flags);
    }

    @NotNull
    @Override
    public Optional<String> getTags() {
        return Optional.ofNullable(tags);
    }

    @NotNull
    @Override
    public Optional<InstallParams> getInstallParams() {
        return Optional.ofNullable(install);
    }

    @NotNull
    @Override
    public Optional<String> getInstallUrl() {
        return Optional.ofNullable(installUrl);
    }
}
