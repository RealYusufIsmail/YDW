/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
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
