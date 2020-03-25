package be.vdab.frida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("taal")
public class TaalController {
    @GetMapping
    public ModelAndView taal(@RequestHeader("accept-language") String language) {
        if (language.contains("nl")) {
            return new ModelAndView("taal", "taal", "Je spreekt Nederlands");
        } else {
            return new ModelAndView("taal", "taal", "Je spreekt geen Nederlands");
        }
    }
}
