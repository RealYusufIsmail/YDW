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
package io.github.realyusufismail.ydw.entities.channel;

import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.Nullable;

public interface Overwrite extends SnowFlake {
    OverwriteType getType();

    String getAllow();

    String getDeny();

    enum OverwriteType {
        ROLE(0),
        MEMBER(1);

        private final int type;

        OverwriteType(int type) {
            this.type = type;
        }

        public static @Nullable OverwriteType getOverwriteType(int type) {
            for (OverwriteType ow : values()) {
                if (ow.getType() == type) {
                    return ow;
                }
            }
            return null;
        }

        public int getType() {
            return type;
        }
    }

}

