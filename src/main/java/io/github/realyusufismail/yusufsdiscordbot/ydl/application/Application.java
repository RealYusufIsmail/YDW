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

package io.github.realyusufismail.yusufsdiscordbot.ydl.application;

import io.github.realyusufismail.yusufsdiscordbot.ydl.application.flags.ApplicationFlag;
import io.github.realyusufismail.yusufsdiscordbot.ydl.application.install.InstallParams;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.Guild;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.User;
import io.github.realyusufismail.yusufsdiscordbot.ydl.team.Team;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import java.util.List;
import java.util.Optional;

public interface Application extends SnowFlake {
    String getName();

    String getDescription();

    String getIcon();

    List<String> getRPCRegions();

    Boolean isPublic();

    Boolean isRequireCodeGrant();

    Optional<String> getTermsOfServiceUrl();

    Optional<String> getPrivacyPolicyUrl();

    Optional<User> getOwner();

    String getVerifyKey();

    Team getTeam();

    Optional<Guild> getGuild();

    Optional<SnowFlake> getPrimarySkuId();

    Optional<String> getSlug();

    Optional<String> getCoverImage();

    Optional<ApplicationFlag[]> getFlags();

    Optional<String> getTags();

    Optional<InstallParams> getInstallParams();

    Optional<String> getInstallUrl();
}
