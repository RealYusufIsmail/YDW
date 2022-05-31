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

package io.github.realyusufismail.ydwreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.activity.ActivityEmoji;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ActivityEmojiReg implements ActivityEmoji {

    private final long id;
    private final String name;
    @NotNull
    private final Boolean animated;

    public ActivityEmojiReg(@NotNull JsonNode emoji, long id) {
        this.id = id;
        this.name = emoji.get("name").asText();
        this.animated = emoji.hasNonNull("animated") && emoji.get("animated").asBoolean();
    }


    @Override
    public String getName() {
        return name;
    }

    @NotNull
    @Override
    public Optional<Boolean> isAnimated() {
        return Optional.ofNullable(animated);
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
