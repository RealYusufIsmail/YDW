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
            
package io.github.realyusufismail.ydwreg.application.commands.slash.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.github.realyusufismail.ydwreg.util.Verify;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Native;

public class Choice {
    /**
     * Max amounts for the options types INTEGER and NUMBER
     */
    @Native
    private static final double MAX_NUMBER_RANGE = 9.0071993e+15;
    /**
     * Min amounts for the options types INTEGER and NUMBER
     */
    @Native
    private static final double MIN_NUMBER_RANGE = -9.0071993e+15;

    private final @NotNull String name;
    private int intValue;
    private double doubleValue;
    private String stringValue;

    public Choice(@NotNull String name, int intValue) {
        Verify.checkLength(name, 100);
        Verify.checkAmount(intValue, (int) MIN_NUMBER_RANGE, (int) MAX_NUMBER_RANGE);
        this.name = name;
        this.intValue = intValue;
    }

    public Choice(@NotNull String name, double doubleValue) {
        Verify.checkLength(name, 100);
        Verify.checkDoubleAmount(doubleValue, MIN_NUMBER_RANGE, MAX_NUMBER_RANGE);
        this.name = name;
        this.doubleValue = doubleValue;
    }

    public Choice(@NotNull String name, @NotNull String stringValue) {
        Verify.checkLength(name, 100);
        Verify.checkLength(stringValue, 100);
        this.name = name;
        this.stringValue = stringValue;
    }

    public @NotNull String getName() {
        return name;
    }

    public int getIntValue() {
        return intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Object getValue() {
        if (intValue != 0) {
            return intValue;
        } else if (doubleValue != 0) {
            return doubleValue;
        } else {
            return stringValue;
        }
    }

    public JsonNode getValueAsJson() {
        if (intValue != 0) {
            return JsonNodeFactory.instance.numberNode(intValue);
        } else if (doubleValue != 0) {
            return JsonNodeFactory.instance.numberNode(doubleValue);
        } else {
            return JsonNodeFactory.instance.textNode(stringValue);
        }
    }
}
