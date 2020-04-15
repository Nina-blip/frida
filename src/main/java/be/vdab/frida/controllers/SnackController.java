package be.vdab.frida.controllers;

import be.vdab.frida.exceptions.SnackNietGevondenException;
import be.vdab.frida.forms.BeginletterForm;
import be.vdab.frida.services.SnackService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("snacks")
class SnackController {
    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final SnackService snackService;

    SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping("alfabet")
    public ModelAndView snacksAlfabet() {
        return new ModelAndView("snackAlfabet", "alfabet", alfabet);
    }

    @GetMapping("{id}")
    public ModelAndView snack(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("snack");
        snackService.read(id).ifPresent(snack -> modelAndView.addObject(snack));
        return modelAndView;
    }

    @GetMapping("alfabet/{letter}")
    public ModelAndView findByBeginLetter(@PathVariable char letter) {
        return new ModelAndView("snackAlfabet", "alfabet", alfabet)
                .addObject("snacks", snackService.findByBeginNaam(String.valueOf(letter)));
    }

    @GetMapping("beginletter/form")
    public ModelAndView beginletterForm() {
        return new ModelAndView("beginletter").addObject(new BeginletterForm(""));
    }

    @GetMapping("beginletter")
    public ModelAndView beginletter(@Valid BeginletterForm form, Errors errors) {
        ModelAndView modelAndView = new ModelAndView("beginletter");
        if (errors.hasErrors()) {
            return modelAndView;
        }
        try {
            return modelAndView.addObject("snacks", snackService.findByBeginNaam(form.getBeginletter()));
        } catch (SnackNietGevondenException ex){
            return modelAndView;
        }
    }

}
