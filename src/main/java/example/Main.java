package example;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.YDWConnector;

public class Main {
    public static void main(String[] args) throws Exception {
        YDW ydw = YDWConnector.setUpBot("").setGuildId("").build();

        ydw.newSlashCommand("ping", "replays with pong");
    }
}
