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
