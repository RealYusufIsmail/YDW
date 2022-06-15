package websocket;

import io.github.realyusufismail.ydw.Status;
import io.github.realyusufismail.ydw.YDWConfig;
import io.github.yusufsdiscordbot.config.Config;

public class Main {
    public static void main(String[] args) throws Exception {
        YDWConfig.setDefault(Config.getString("TOKEN")).setStatus(Status.ONLINE).build();
    }
}
