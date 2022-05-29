/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package yusufsdiscordbot.ydlreg.util;

import api.ydl.exception.InvalidTokenException;
import api.ydl.exception.VerifyException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class Verify {
    private Verify() {
        throw new IllegalStateException("Utility class");
    }

    public static void checkIfNull(@Nullable Object o) {
        if (o == null) {
            throw new VerifyException("The provided object is null!");
        }
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

    public static void checkAmount(long amount, long maxAmount) {
        if (amount > maxAmount) {
            throw new VerifyException("Amount is too high! The maximum amount is " + maxAmount);
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

    public static void verifyToken(boolean b, String s) throws InvalidTokenException {
        if (!b) {
            throw new InvalidTokenException(s);
        }
    }

    public static void checkIfUrl(String url, @NotNull Pattern allowedStreamingUrls) {
        if (!allowedStreamingUrls.matcher(url).matches()) {
            throw new VerifyException("The provided url is not a valid url!");
        }
    }
}