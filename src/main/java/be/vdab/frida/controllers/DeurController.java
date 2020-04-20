package be.vdab.frida.controllers;

import be.vdab.frida.sessions.Deur;
import be.vdab.frida.sessions.Frietspel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
@RequestMapping("frietspel")
public class DeurController {
    private final Frietspel frietspel;

    public DeurController(Frietspel frietspel) {
        this.frietspel = frietspel;
    }

    @GetMapping("zoeken")
    public ModelAndView frietspel() {
        return new ModelAndView("frietspel").addObject(frietspel);
    }
    @PostMapping("zoeken/nieuwspel")
    public String nieuwSpel() {
        frietspel.reset();
        return "redirect:/frietspel/zoeken";
    }
    @PostMapping("zoeken/opendeur")
    public String openDeur(int index) {
        frietspel.openDeur(index);
        return "redirect:/frietspel/zoeken";
    }
}
