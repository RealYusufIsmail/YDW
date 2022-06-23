package io.github.realyusufismail.ydwreg.rest.request;

import io.github.realyusufismail.ydw.YDWInfo;
import okhttp3.Request;

public class YDWRequest {

    public Request.Builder request(String token, String url) {
        return new Request.Builder().header("Content-Type", "application/json")
            .header("Authorization", "Bot " + token)
            .header("user-agent",
                    "DiscordBot (" + YDWInfo.ydw_GITHUB + ", " + YDWInfo.ydw_VERSION + ")")
            .header("accept-encoding", "json")
            .url(url);
    }
}
