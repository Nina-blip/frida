package be.vdab.frida.controllers;


import be.vdab.frida.domain.Saus;
import be.vdab.frida.repositories.CSVSausRepository;
import be.vdab.frida.repositories.SausRepository;
import be.vdab.frida.services.DefaultSausService;
import be.vdab.frida.services.SausService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Controller
@RequestMapping("sauzen")
class SauzenController {
    /*private Saus[] sauzen = {new Saus(1L, "Cocktail", new String[]{"mayonnaise", "ketchup", "whiskey"}),
    new Saus(2L, "Mayonnaise", new String[]{"ei", "olie"}),
    new Saus(3L, "Mosterd", new String[]{"mosterdzaad", "olie", "azijn", "kruiden"}),
    new Saus(4L, "Tartare", new String[]{"mayonnaise", "bieslook"}),
    new Saus(5L, "Vinaigrette", new String[]{"olie", "azijn"})};*/

    private final SausService sausService;

    public SauzenController(SausService service) {
        this.sausService = service;
    }

    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    @GetMapping
    public ModelAndView sauzen(){
        return new ModelAndView("sauzen", "sauzen", sausService.findAll());
    }

    @GetMapping("{id}")
    public ModelAndView saus(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("saus");
        sausService.findById(id).ifPresent(saus -> modelAndView.addObject(saus));
        return modelAndView;
    }

    @GetMapping("alfabet")
    public ModelAndView alfabet() {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet);
    }

    @GetMapping("alfabet/{letter}")
    public ModelAndView sauzenBeginnendMet(@PathVariable char letter) {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet)
                .addObject("sauzen", sausService.findByNaamBegintMet(letter));
    }
}
