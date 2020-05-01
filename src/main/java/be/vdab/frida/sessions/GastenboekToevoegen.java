package be.vdab.frida.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class GastenboekToevoegen implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean wilToevoegen = false;

    public boolean isWilToevoegen() {
        return wilToevoegen;
    }

    public void setWilToevoegen(boolean wilToevoegen) {
        this.wilToevoegen = wilToevoegen;
    }
}
