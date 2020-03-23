package be.vdab.frida.controllers;


import be.vdab.frida.domain.Saus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sauzen")
class SauzenController {
    private Saus[] sauzen = {new Saus(1L, "Cocktail", new String[]{"mayonnaise", "ketchup", "whiskey"}),
    new Saus(2L, "Mayonnaise", new String[]{"ei", "olie"}),
    new Saus(3L, "Mosterd", new String[]{"mosterdzaad", "olie", "azijn", "kruiden"}),
    new Saus(4L, "Tartare", new String[]{"mayonnaise", "bieslook"}),
    new Saus(5L, "Vinaigrette", new String[]{"olie", "azijn"})};

    @GetMapping
    public ModelAndView sauzen(){
        return new ModelAndView("sauzen", "sauzen", sauzen);
    }
}
