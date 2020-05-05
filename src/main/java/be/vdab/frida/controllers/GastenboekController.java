package be.vdab.frida.controllers;

import be.vdab.frida.domain.GastenboekEntry;
import be.vdab.frida.services.GastenboekService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("gastenboek")
class GastenboekController {
    private final GastenboekService gastenboekService;

    public GastenboekController(GastenboekService gastenboekService) {
        this.gastenboekService = gastenboekService;
    }

    @GetMapping
    public ModelAndView gastenboek(){
        ModelAndView modelAndView = new ModelAndView("gastenboek");
        modelAndView.addObject("boodschappen", gastenboekService.findAll());
        modelAndView.addObject(new GastenboekEntry(0, null, null));
        return modelAndView;
    }
    
    @PostMapping
    public String entryToevoegen(@Valid @ModelAttribute("gastenboekEntry") GastenboekEntry gastenboekEntry, Errors errors){
        if (errors.hasErrors()){
            return "gastenboek";
        }
        gastenboekService.toevoegen(gastenboekEntry);
        return "redirect:/gastenboek";
    }

}
