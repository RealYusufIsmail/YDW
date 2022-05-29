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

package yusufsdiscordbot.ydlreg.application;

import api.ydl.snowflake.SnowFlake;
import com.fasterxml.jackson.databind.JsonNode;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.application.Application;
import yusufsdiscordbot.ydl.application.flags.ApplicationFlag;
import yusufsdiscordbot.ydl.application.install.InstallParams;
import yusufsdiscordbot.ydl.application.team.Team;
import yusufsdiscordbot.ydl.entities.Guild;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydlreg.application.install.InstallParamsReg;
import yusufsdiscordbot.ydlreg.application.team.TeamReg;
import yusufsdiscordbot.ydlreg.entities.UserReg;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationReg implements Application {
    private final YDL ydl;
    private final long id;

    private final String name;
    private final String description;
    private final String icon;
    private final List<String> rpcRegions = new ArrayList<>();
    private final Boolean isPublic;
    private final Boolean isRequireCodeGrant;
    private final String termsOfServiceUrl;
    private final String privacyPolicyUrl;
    private final User owner;
    private final String verifyKey;
    private final Team team;
    private final Guild guild;
    private final Long primarySkuId;
    private final String slug;
    private final String coverImage;
    private final ApplicationFlag[] flags;
    private final String tags;
    private final InstallParams install;
    private final String installUrl;

    public ApplicationReg(JsonNode application, long id, YDL ydl) {
        this.ydl = ydl;
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
                                application.get("owner").get("id").asLong(), ydl)
                        : null;
        this.verifyKey = application.get("verify_key").asText();
        this.team =
                application.hasNonNull("team") ? new TeamReg(application.get("team"), ydl) : null;
        this.guild = application.hasNonNull("guild_id")
                ? ydl.getGuild(application.get("guild_id").asLong())
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
        if (application.has("rpc_regions")) {
            application.get("rpc_regions").forEach(rpcRegion -> rpcRegions.add(rpcRegion.asText()));
        }
    }

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

    @Override
    public Optional<String> getTermsOfServiceUrl() {
        return Optional.ofNullable(termsOfServiceUrl);
    }

    @Override
    public Optional<String> getPrivacyPolicyUrl() {
        return Optional.ofNullable(privacyPolicyUrl);
    }

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

    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @Override
    public Optional<SnowFlake> getPrimarySkuId() {
        return Optional.of(SnowFlake.of(primarySkuId));
    }

    @Override
    public Optional<String> getSlug() {
        return Optional.ofNullable(slug);
    }

    @Override
    public Optional<String> getCoverImage() {
        return Optional.ofNullable(coverImage);
    }

    @Override
    public Optional<ApplicationFlag[]> getFlags() {
        return Optional.ofNullable(flags);
    }

    @Override
    public Optional<String> getTags() {
        return Optional.ofNullable(tags);
    }

    @Override
    public Optional<InstallParams> getInstallParams() {
        return Optional.ofNullable(install);
    }

    @Override
    public Optional<String> getInstallUrl() {
        return Optional.ofNullable(installUrl);
    }
}
