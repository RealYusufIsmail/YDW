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
package io.github.realyusufismail.ydwreg.application.commands.slash.builder;

import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydw.application.commands.slash.builder.OptionExtenderConfig;

import java.util.Collection;

public class OptionExtender extends Option implements OptionExtenderConfig {

    private final OptionType type;
    private final String name;
    private final String description;
    private final Boolean required;

    private Collection<Choice> choices;

    public OptionExtender(OptionType type, String name, String description, boolean isRequired) {
        super(type, name, description, isRequired);
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = isRequired;
    }

    public OptionExtender(OptionType type, String name, String description) {
        this(type, name, description, false);
    }

    @Override
    public OptionExtenderConfig addChoice(String name, String value) {
        choices.add(new Choice(name, value));
        return this;
    }

    @Override
    public OptionExtenderConfig addChoice(String name, int value) {
        choices.add(new Choice(name, value));
        return this;
    }

    @Override
    public OptionExtenderConfig addChoice(String name, double value) {
        choices.add(new Choice(name, value));
        return this;
    }

    @Override
    public OptionExtenderConfig addChoices(Collection<Choice> choices) {
        this.choices = choices;
        return this;
    }

    // TODO: add max and min
    @Override
    public OptionExtenderConfig setMaxAndMin(Integer max, Integer min) {
        return null;
    }

    @Override
    public OptionExtenderConfig setMaxAndMin(Double max, Double min) {
        return null;
    }

    public Collection<Choice> getChoices() {
        return choices;
    }
}
