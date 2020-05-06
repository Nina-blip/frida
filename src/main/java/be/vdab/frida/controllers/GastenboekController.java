package be.vdab.frida.controllers;

import be.vdab.frida.domain.GastenboekEntry;
import be.vdab.frida.forms.GastenboekForm;
import be.vdab.frida.services.GastenboekService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("gastenboek")
class GastenboekController {
    private final GastenboekService gastenboekService;

    public GastenboekController(GastenboekService gastenboekService) {
        this.gastenboekService = gastenboekService;
    }

    @GetMapping
    public ModelAndView gastenboek() {
        ModelAndView modelAndView = new ModelAndView("gastenboek");
        modelAndView.addObject("boodschappen", gastenboekService.findAll());
        modelAndView.addObject(new GastenboekForm(0, null, null));
        return modelAndView;
    }

    @PostMapping
    public String entryToevoegen(@Valid GastenboekForm form, Errors errors) {
        if (errors.hasErrors()) {
            return "gastenboek";
        }
        gastenboekService.toevoegen(new GastenboekEntry(form.getId(), form.getNaam(), form.getBoodschap()));
        return "redirect:/gastenboek";
    }

    @PostMapping("verwijderen")
    public String verwijderen(long[] id) {
        if (id.length == 0) {
            return "gastenboek";
        }
        for (long x :id) {
            gastenboekService.delete(x);
        }
        return "redirect:/gastenboek";
    }

}
