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

package yusufsdiscordbot.ydlreg.entities.embed.builder;

import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.entities.embed.Embed;


// TODO: requires full rewrite.
public class EmbedBuilder {
    /**
     * Tile max length is 256 characters.
     */
    private static final int MAX_TITLE_LENGTH = 256;
    /**
     * Description max length is 2048 characters.
     */
    private static final int MAX_DESCRIPTION_LENGTH = 4096;
    /**
     * Footer max length is 2048 characters.
     */
    private static final int MAX_FOOTER_LENGTH = 2048;
    /**
     * Field name max length is 256 characters.
     */
    private static final int MAX_FIELD_NAME_LENGTH = 256;
    /**
     * Field value max length is 1024 characters.
     */
    private static final int MAX_FIELD_VALUE_LENGTH = 1024;
    /**
     * Max fields per embed is 25.
     */
    private static final int MAX_FIELDS = 25;
    /**
     * Max length for author name is 256 characters.
     */
    private static final int MAX_AUTHOR_NAME_LENGTH = 256;



    public @NotNull Embed build() {
        // TODO: implement
        return null;
    }



}
