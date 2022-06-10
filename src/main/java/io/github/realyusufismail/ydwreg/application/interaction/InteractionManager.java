package io.github.realyusufismail.ydwreg.application.interaction;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.Interaction;

public class InteractionManager {

    private final YDW ydw;

    private static Interaction interaction;


    public InteractionManager(YDW ydw) {
        this.ydw = ydw;
    }

    public void addInteraction(Interaction interactionReg) {
        interaction = interactionReg;
    }

    public Interaction getInteraction() {
        return interaction;
    }
}
