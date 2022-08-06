package io.github.realyusufismail.ydw.entities.automod;

import java.util.EnumSet;
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


    /**
     * Gets the internally pre-defined wordsets which will be searched for in content
     * <p>
     * Associated with the trigger type {@link TriggerType#KEYWORD_PRESET}
     *
     * @return the internally pre-defined wordsets which will be searched for in content
     */
    EnumSet<KeywordPresetType> getKeyWordPreset();
}
