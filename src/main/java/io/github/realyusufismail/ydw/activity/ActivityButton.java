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

package io.github.realyusufismail.ydw.activity;

public interface ActivityButton {

    /**
     * Gets the text shown on the button. (1- 32 characters)
     *
     * @return The text shown on the button.
     */
    String getLabel();

    /**
     * Gets the url which is opened when the button is clicked. (1- 512 characters)
     *
     * @return The url which is opened when the button is clicked.
     */
    String getUrl();
}
