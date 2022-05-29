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

package yusufsdiscordbot.ydlreg.entities.embed.objects;

import com.fasterxml.jackson.databind.JsonNode;
import yusufsdiscordbot.ydl.entities.embed.objects.Video;

public class VideoReg implements Video {
    private final JsonNode video;
    private String url;
    private String proxyUrl;
    private int width;
    private int height;

    public VideoReg(JsonNode video) {
        this.video = video;
    }

    @Override
    public String getUrl() {
        return video.get("url").asText();
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getProxyUrl() {
        return video.get("proxy_url").asText();
    }

    @Override
    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    @Override
    public int getHeight() {
        return video.get("height").asInt();
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return video.get("width").asInt();
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }
}
