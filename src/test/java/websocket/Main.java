package websocket;

import io.github.realyusufismail.websocket.event.events.ReadyEvent;
import io.github.realyusufismail.ydw.Status;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.YDWConfig;
import io.github.yusufsdiscordbot.config.Config;

public class Main {
    public static void main(String[] args) throws Exception {
        YDW ydw = YDWConfig.setDefault(Config.getString("TOKEN")).setStatus(Status.ONLINE).build();

        // ydw.setGuildId("938122131949097052");

        // ydw.awaitReady().newSlashCommand("ping", "responds with pong");

        // ydw.onEvent(ReadyEvent.class).subscribe(event -> {
        // System.out.println("Ready!");
        // });
    }
}
