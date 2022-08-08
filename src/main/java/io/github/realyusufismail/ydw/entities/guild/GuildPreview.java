/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.emoji.Emoji;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydwreg.entities.guild.GuildFeatures;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

import java.util.EnumSet;
import java.util.List;

public interface GuildPreview extends SnowFlake, GenericEntity {

    String getName();

    String getIcon();

    String getSplash();

    String getDiscoverySplash();

    List<Emoji> getEmojis();

    EnumSet<GuildFeatures> getFeatures();

    Integer getApproximateMemberCount();

    Integer getApproximatePresenceCount();

    String getDescription();

    List<Sticker> getStickers();
}
