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

package io.github.realyusufismail.ydw.application;

import io.github.realyusufismail.ydw.application.flags.ApplicationFlag;
import io.github.realyusufismail.ydw.application.install.InstallParams;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.team.Team;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

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
