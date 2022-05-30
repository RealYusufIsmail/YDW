package yusufsdiscordbot.ydl.activity;

import java.util.Optional;

public interface ActivityTimeStamp  {

    /**
     * Gets the unix time (in milliseconds) of when the activity started.
     * @return the unix time (in milliseconds) of when the activity started/
     */
    Optional<Integer> getStart();

    /**
     * Gets the unix time (in milliseconds) of when the activity ends.
     * @return the unix time (in milliseconds) of when the activity ends.
     */
    Optional<Integer> getEnd();
}
