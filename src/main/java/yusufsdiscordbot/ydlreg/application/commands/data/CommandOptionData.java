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

package yusufsdiscordbot.ydlreg.application.commands.data;

import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.application.commands.option.OptionTypeEnum;
import yusufsdiscordbot.ydlreg.util.Verify;

import java.lang.annotation.Native;
import java.util.List;
import java.util.function.Consumer;

public class CommandOptionData {
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
    private static final String RANGE_ERROR =
            "you can only have the rang for the options Number and Integer.";
    private final @NotNull String name;
    private final @NotNull String description;
    private final boolean isRequired;
    private final OptionTypeEnum optionType;
    private boolean isAutoComplete;
    private double doubleMaxRange;
    private double doubleMinRange;
    private int intMaxRange;
    private int intMinRange;
    private List<Choice> choices;

    /**
     * The command option constructor.
     *
     * @param optionType The type of the option.
     * @param name The name of the option.
     * @param description The description of the option.
     */
    public CommandOptionData(OptionTypeEnum optionType, @NotNull String name,
            @NotNull String description) {
        this.isRequired = false;
        Verify.checkLength(name, 32);
        Verify.checkLength(description, 100);
        this.optionType = optionType;
        this.name = name;
        this.description = description;
    }


    /**
     * The command option constructor.
     *
     * @param name The name of the option.
     * @param description The description of the option.
     * @param isRequired Whether the option is required. The default is false.
     * @param optionType The type of the option.
     */
    public CommandOptionData(@NotNull OptionTypeEnum optionType, @NotNull String name,
            @NotNull String description, boolean isRequired) {
        Verify.checkLength(name, 32);
        Verify.checkLength(description, 100);
        if (optionType.equals(OptionTypeEnum.INTEGER)) {
            Verify.checkAmount(optionType.getValue(), (int) MIN_NUMBER_RANGE,
                    (int) MAX_NUMBER_RANGE);
        } else if (optionType == OptionTypeEnum.NUMBER) {
            Verify.checkDoubleAmount(optionType.getValue(), MIN_NUMBER_RANGE, MAX_NUMBER_RANGE);
        }
        this.optionType = optionType;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
    }

    public boolean setRequired(boolean isRequired) {
        return this.isRequired == isRequired;
    }

    public @NotNull CommandOptionData setMaxRange(int maxAmount) {
        if (optionType == OptionTypeEnum.INTEGER) {
            Verify.checkAmount(maxAmount, (int) MIN_NUMBER_RANGE, (int) MAX_NUMBER_RANGE);
            this.intMaxRange = maxAmount;
            return this;
        } else {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    public @NotNull CommandOptionData setMinRange(int minAmount) {
        if (optionType == OptionTypeEnum.INTEGER) {
            Verify.checkAmount(minAmount, (int) MIN_NUMBER_RANGE, (int) MAX_NUMBER_RANGE);
            this.intMinRange = minAmount;
            return this;
        } else {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    public @NotNull CommandOptionData setMaxRange(double maxAmount) {
        if (optionType == OptionTypeEnum.NUMBER) {
            Verify.checkDoubleAmount(maxAmount, MIN_NUMBER_RANGE, MAX_NUMBER_RANGE);
            this.doubleMaxRange = maxAmount;
            return this;
        } else {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    public @NotNull CommandOptionData setMinRange(double minAmount) {
        if (optionType == OptionTypeEnum.NUMBER) {
            Verify.checkDoubleAmount(minAmount, MIN_NUMBER_RANGE, MAX_NUMBER_RANGE);
            this.doubleMinRange = minAmount;
            return this;
        } else {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    public @NotNull CommandOptionData setRange(int minAmount, int maxAmount) {
        if (optionType == OptionTypeEnum.INTEGER) {
            Verify.checkAmount(minAmount, (int) MIN_NUMBER_RANGE, (int) MAX_NUMBER_RANGE);
            Verify.checkAmount(maxAmount, (int) MIN_NUMBER_RANGE, (int) MAX_NUMBER_RANGE);
            this.intMaxRange = maxAmount;
            this.intMinRange = minAmount;
            return this;
        } else {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    public @NotNull CommandOptionData setRange(double minAmount, double maxAmount) {
        if (optionType == OptionTypeEnum.NUMBER) {
            Verify.checkDoubleAmount(minAmount, MIN_NUMBER_RANGE, MAX_NUMBER_RANGE);
            Verify.checkDoubleAmount(maxAmount, MIN_NUMBER_RANGE, MAX_NUMBER_RANGE);
            this.doubleMaxRange = maxAmount;
            this.doubleMinRange = minAmount;
            return this;
        } else {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    public @NotNull CommandOptionData setChoice(@NotNull String name, int value) {
        new Choice(name, value);
        choices.add(new Choice(name, value));
        return this;
    }

    public @NotNull CommandOptionData setChoice(@NotNull String name, double value) {
        new Choice(name, value);
        choices.add(new Choice(name, value));
        return this;
    }

    public @NotNull CommandOptionData setChoice(@NotNull String name, @NotNull String value) {
        new Choice(name, value);
        choices.add(new Choice(name, value));
        return this;
    }

    public boolean isAutoComplete(boolean isAutoComplete) {
        if (optionType == OptionTypeEnum.STRING || optionType == OptionTypeEnum.INTEGER
                || optionType == OptionTypeEnum.NUMBER) {
            return this.isAutoComplete == isAutoComplete;
        } else {
            throw new IllegalArgumentException(
                    "you can only set autocomplete for the options String, Integer and Number.");
        }
    }

    public boolean isAutoComplete() {
        return isAutoComplete;
    }

    public void setAutoComplete(boolean autoComplete) {
        isAutoComplete = autoComplete;
    }

    public double getDoubleMaxRange() {
        return doubleMaxRange;
    }

    public double getDoubleMinRange() {
        return doubleMinRange;
    }

    public int getIntMaxRange() {
        return intMaxRange;
    }

    public int getIntMinRange() {
        return intMinRange;
    }

    public @NotNull String getOptionName() {
        return name;
    }

    public @NotNull String getOptionDescription() {
        return description;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public @NotNull CommandOptionData setChoices(@NotNull List<Choice> choices) {
        this.choices = choices;
        for (Choice choice : choices) {
            if (choice.getValue() instanceof String) {
                choices.add(new Choice(choice.getName(), choice.getValue().toString()));
            } else if (choice.getValue() instanceof Integer) {
                choices.add(new Choice(choice.getName(), (int) choice.getValue()));
            } else if (choice.getValue() instanceof Double) {
                choices.add(new Choice(choice.getName(), (double) choice.getValue()));
            } else {
                throw new IllegalArgumentException("Invalid choice value");
            }
        }
        return this;
    }

    public void ifPresent(@NotNull Consumer<CommandOptionData> consumer) {
        consumer.accept(this);
    }

    public OptionTypeEnum getOptionType() {
        return optionType;
    }

    public static class Choice {
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
    }
}
