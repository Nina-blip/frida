package be.vdab.frida.controllers;


import be.vdab.frida.domain.Saus;
import be.vdab.frida.forms.SausRadenForm;
import be.vdab.frida.repositories.CSVSausRepository;
import be.vdab.frida.repositories.SausRepository;
import be.vdab.frida.services.DefaultSausService;
import be.vdab.frida.services.SausService;
import be.vdab.frida.sessions.SausRaden;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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

    private final SausRaden sausRaden;
    private final SausService sausService;

    public SauzenController(SausRaden sausRaden, SausService sausService) {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            alfabet[letter - 'a'] = letter;
        }
        this.sausRaden = sausRaden;
        this.sausService = sausService;
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

    private String randomSaus() {
        List<Saus> sauzen = sausService.findAll();
        return sauzen.get(
                ThreadLocalRandom.current().nextInt(sauzen.size())).getNaam();
    }

    @GetMapping("raden")
    public ModelAndView radenForm() {
        sausRaden.reset(randomSaus());
        return new ModelAndView("sausRaden").addObject(sausRaden)
                .addObject(new SausRadenForm('_'));
    }
    @PostMapping("raden/nieuwspel")
    public String radenNieuwSpel() {
        return "redirect:/sauzen/raden";
    }
    @PostMapping(value = "raden")
    public ModelAndView raden(@Valid SausRadenForm form, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("sausRaden").addObject(sausRaden);
        }
        sausRaden.gok(form.getLetter());
        return new ModelAndView("redirect:/sauzen/raden/volgendegok");
    }
    @GetMapping("raden/volgendegok")
    public ModelAndView volgendeGok() {
        return new ModelAndView("sausRaden").addObject(sausRaden)
                .addObject(new SausRadenForm('_'));
    }
}
