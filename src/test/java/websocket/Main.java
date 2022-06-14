package websocket;

import com.neovisionaries.ws.client.WebSocketException;
import io.github.realyusufismail.ydw.GateWayIntent;
import io.github.realyusufismail.ydw.Status;
import io.github.realyusufismail.ydwreg.exception.InvalidStatusException;
import io.github.yusufsdiscordbot.config.Config;

import java.io.IOException;

public class Main {
    public static void main(String[] args)
            throws WebSocketException, IOException, InvalidStatusException {
        YDwConfig.setDefault(Config.getString("TOKEN")).setStatus(Status.ONLINE).build();
    }
}
