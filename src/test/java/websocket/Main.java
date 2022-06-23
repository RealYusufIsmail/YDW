package websocket;

import io.github.realyusufismail.websocket.event.events.ReadyEvent;
import io.github.realyusufismail.ydw.Status;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.YDWConfig;
import io.github.yusufsdiscordbot.config.Config;

public class Main {
    public static void main(String[] args) throws Exception {
        YDW ydw = YDWConfig.setDefault(Config.getString("TOKEN"))
            .setStatus(Status.ONLINE)
            .setGuildId("938122131949097052")
            .build();

        ydw.awaitReady().newSlashCommand("ping", "responds with pong").call();

        ydw.awaitReady()
            .newSlashCommand("guild", "A guild only command")
            .setToGuildOnly(true)
            .call();

        ydw.awaitReady().newSlashCommand("test1", "test1").setToGuildOnly(true).call();

        ydw.setEventHandler(new TestHandler());
    }
}
