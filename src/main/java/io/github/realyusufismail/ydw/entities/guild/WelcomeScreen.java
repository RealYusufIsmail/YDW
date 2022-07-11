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
