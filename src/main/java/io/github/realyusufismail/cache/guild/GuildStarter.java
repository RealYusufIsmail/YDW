package io.github.realyusufismail.cache.guild;

public class GuildStarter {
    private final long id;
    private final GuildCacheHandler guildCacheHandler;
    private final GuildCacheHandler.Type type;

    public GuildStarter(long id, GuildCacheHandler guildCacheHandler, GuildCacheHandler.Type type) {
        this.id = id;
        this.guildCacheHandler = guildCacheHandler;
        this.type = type;
    }
}
