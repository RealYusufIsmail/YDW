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

package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.GenericEntity;

import java.util.List;
import java.util.Optional;

public interface WelcomeScreen extends GenericEntity {
    Optional<String> getDescription();

    /**
     * the channels shown in the welcome screen, up to 5
     *
     * @return the channels shown in the welcome screen, up to 5
     */
    List<WelcomeScreenChannel> getWelcomeChannels();
}
