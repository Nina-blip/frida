package be.vdab.frida.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GastenboekEntry {
    private long id;
    private LocalDate datum;
    @NotBlank
    private String naam;
    @NotBlank
    private String boodschap;


    public GastenboekEntry(){
    }

    public GastenboekEntry(long id, String naam, String boodschap) {
        this.id = id;
        this.datum =  LocalDate.now();
        this.naam = naam;
        this.boodschap = boodschap;
    }

    public GastenboekEntry(long id, LocalDate datum, String naam, String boodschap){
        this.id = id;
        this.datum= datum;
        this.naam = naam;
        this.boodschap = boodschap;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getDatumAlsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        return formatter.format(datum);
    }

    public String getNaam() {
        return naam;
    }

    public String getBoodschap() {
        return boodschap;
    }

    public long getId() {
        return id;
    }
}
