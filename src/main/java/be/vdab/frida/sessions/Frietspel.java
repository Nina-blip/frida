package be.vdab.frida.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@SessionScope
public class Frietspel implements Serializable {
    private final static long serialVersionUID = 1L;
    private final Deur[] deuren = new Deur[7];

    public Frietspel() {
        reset();
    }

    public void openDeur(int index) {
        deuren[index].open();
    }

    public Deur[] getDeuren() {
        return deuren;
    }

    public void reset() {
        int indexMetFriet = ThreadLocalRandom.current().nextInt(7);
        for (int index = 0; index != 7; index++) {
            deuren[index] = new Deur(index, index == indexMetFriet);
        }
    }
}
