package be.vdab.frida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/")
class IndexController {
    @GetMapping
    public ModelAndView index(){
        int dag = LocalDate.now().getDayOfWeek().getValue();
        if (dag == 1 || dag == 4){
            return new ModelAndView("index", "boodschap", "gesloten");
        } else {
            return new ModelAndView("index", "boodschap", "open");
        }
    }
}
