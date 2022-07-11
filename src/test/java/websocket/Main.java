package websocket;

import io.github.realyusufismail.ydw.Status;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.YDWConfig;
import io.github.realyusufismail.ydw.application.commands.slash.builder.SlashCommandBuilder;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.SlashCommandBuilderReg;
import io.github.yusufsdiscordbot.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // logger
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        YDW ydw = YDWConfig.setDefault(Config.getString("TOKEN"))
            .setStatus(Status.ONLINE)
            .setGuildId("938122131949097052")
            .build();

        ydw.addEventAdapter(new Handler(), new SlashCommandHandler());


        List<SlashCommandBuilder> slashCommandBuilders = new ArrayList<>();

        ydw.awaitReady().upsertCommands(slashCommandBuilders);
    }
}
