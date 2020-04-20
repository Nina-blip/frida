package be.vdab.frida.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

public class Deur implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int index;
    private final Boolean bevatFriet;
    private Boolean geopend = false;

    public Deur(int index, Boolean bevatFriet) {
        this.index = index;
        this.bevatFriet = bevatFriet;
    }


    public void open(){
        this.geopend= true;
    }

    public int getIndex() {
        return index;
    }

    public Boolean getBevatFriet() {
        return bevatFriet;
    }

    public Boolean getGeopend() {
        return geopend;
    }
}
