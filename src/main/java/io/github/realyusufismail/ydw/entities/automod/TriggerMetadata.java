package io.github.realyusufismail.ydw.entities.automod;

import java.util.List;

public interface TriggerMetadata {

    /**
     * Gets the substrings that will be searched for in the content of a message.
     * <p>
     * Associated with the trigger type {@link TriggerType#KEYWORD}
     *
     * @return the substrings that will be searched for in content
     */
    List<String> getKeyWordFilter();

}
