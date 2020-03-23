package be.vdab.frida.controllers;

import be.vdab.frida.domain.Adres;
import be.vdab.frida.domain.Gemeente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/")
class IndexController {
    private String boodschap(){
        int dag = LocalDate.now().getDayOfWeek().getValue();
        if (dag == 1 || dag == 4){
            return "gesloten";
        } else {
            return "open";
        }
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index", "boodschap", boodschap());
        modelAndView.addObject("frituurAdres", new Adres("Zandstraat ", "133", new Gemeente("Turnhout", 2300)));
        return modelAndView;
    }
}
