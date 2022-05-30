package io.github.realyusufismail.ydw.ydl.activity;

import java.util.Optional;

public interface ActivitySecret {

    /**
     * Gets the secrete for joining a party.
     * 
     * @return The secrete.
     */
    Optional<String> getJoin();

    /**
     * Gets the secrete for spectating a game.
     * 
     * @return The secrete.
     */
    Optional<String> getSpectate();

    /**
     * Gets the secrete for the specified instanced match.
     * 
     * @return The secrete.
     */
    Optional<String> getMatch();
}
