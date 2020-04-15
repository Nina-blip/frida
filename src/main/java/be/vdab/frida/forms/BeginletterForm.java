package be.vdab.frida.forms;

import javax.validation.constraints.NotBlank;

public class BeginletterForm {
    @NotBlank

    private final String beginletter;

    public BeginletterForm(String beginletter) {
        this.beginletter = beginletter;
    }

    public String getBeginletter() {
        return beginletter;
    }
}
