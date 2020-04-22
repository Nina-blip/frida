package be.vdab.frida.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SausRadenForm {
    @NotNull
    private final char letter;

    public SausRadenForm(@NotNull char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }
}
