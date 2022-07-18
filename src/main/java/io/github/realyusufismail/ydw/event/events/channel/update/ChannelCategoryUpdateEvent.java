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
package io.github.realyusufismail.ydw.event.events.channel.update;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;

public class ChannelCategoryUpdateEvent extends BasicChannelUpdateEvent<Category> {

    public ChannelCategoryUpdateEvent(YDW ydw, GuildChannel channel, Category oldCategory,
            Category newCategory) {
        super(ydw, channel, oldCategory, newCategory);
    }

    public Category getOldCategory() {
        return oldValue;
    }

    public Category getNewCategory() {
        return newValue;
    }
}
