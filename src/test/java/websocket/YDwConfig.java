package websocket;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class YDwConfig {
    public final String token;

    private YDwConfig(String token) {
        this.token = token;
    }

    @NotNull
    public static YDwConfig setToken(String token) {
        return new YDwConfig(token);
    }
}
