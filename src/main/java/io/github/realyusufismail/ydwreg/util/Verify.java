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
            
package io.github.realyusufismail.ydwreg.util;

import io.github.realyusufismail.ydwreg.exception.VerifyException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class Verify {
    private Verify() {
        throw new IllegalStateException("Utility class");
    }

    public static void checkIfNull(@Nullable Object o, String objectName) {
        if (o == null) {
            throw new VerifyException("The provided object " + objectName + " is null!");
        }
    }

    public static void checkLength(@NotNull String input, int maxLength) {
        if (input.length() > maxLength) {
            throw new VerifyException("The provided input is too long. The maximum length is "
                    + maxLength + " characters.");
        }
    }

    public static void checkAmount(int amount, int maxAmount) {
        if (amount > maxAmount) {
            throw new VerifyException("Amount is too high! The maximum amount is " + maxAmount);
        }
    }

    public static void checkAmount(int amount, int minAmount, int maxAmount) {
        if (amount < minAmount || amount > maxAmount) {
            throw new VerifyException("Amount is out of range! The minimum amount is " + minAmount
                    + " and the maximum amount is " + maxAmount);
        }
    }

    public static void checkDoubleAmount(double amount, double minAmount, double maxAmount) {
        if (amount < minAmount || amount > maxAmount) {
            throw new VerifyException("Amount is out of range! The minimum amount is " + minAmount
                    + " and the maximum amount is " + maxAmount);
        }
    }

    public static void verify(boolean b, String s) {
        if (!b) {
            throw new VerifyException(s);
        }
    }

    public static void checkIfUrl(@NotNull String url, @NotNull Pattern allowedStreamingUrls) {
        if (!allowedStreamingUrls.matcher(url).matches()) {
            throw new VerifyException("The provided url is not a valid url!");
        }
    }
}
