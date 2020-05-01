package be.vdab.frida.controllers;

import be.vdab.frida.domain.GastenboekEntry;
import be.vdab.frida.services.GastenboekService;
import be.vdab.frida.sessions.GastenboekToevoegen;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("gastenboek")
class GastenboekController {
    private final GastenboekService gastenboekService;
    private GastenboekToevoegen gastenboekToevoegen = new GastenboekToevoegen();

    public GastenboekController(GastenboekService gastenboekService) {
        this.gastenboekService = gastenboekService;
    }

    @GetMapping
    public ModelAndView gastenboek(){
        ModelAndView modelAndView = new ModelAndView("gastenboek");
        modelAndView.addObject("boodschappen", gastenboekService.findAll());
        modelAndView.addObject(gastenboekToevoegen);
        return modelAndView;
    }

    @GetMapping("toevoegen")
    public ModelAndView gastenboekToevoegen(){
        ModelAndView modelAndView = new ModelAndView("gastenboek");
        modelAndView.addObject(new GastenboekEntry(null, null));
        modelAndView.addObject("boodschappen", gastenboekService.findAll());
        gastenboekToevoegen.setWilToevoegen(true);
        modelAndView.addObject(gastenboekToevoegen.isWilToevoegen());
        return modelAndView;
    }

    @PostMapping("toevoegen/entry")
    public String entryToevoegen(@Valid GastenboekEntry entry, Errors errors){
        if (errors.hasErrors()){
            return "toevoegen";
        }
        gastenboekService.toevoegen(entry);
        gastenboekToevoegen.setWilToevoegen(false);
        return "redirect:/gastenboek";
    }

}
