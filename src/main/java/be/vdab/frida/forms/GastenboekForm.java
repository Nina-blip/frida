package be.vdab.frida.forms;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class GastenboekForm {
    private final long id;
    @NotBlank
    private final String naam, boodschap;

    public GastenboekForm(long id, String naam, String boodschap) {
        this.id = id;
        this.naam = naam;
        this.boodschap = boodschap;
    }

    public long getId() {
        return id;
    }


    public String getNaam() {
        return naam;
    }

    public String getBoodschap() {
        return boodschap;
    }
}
