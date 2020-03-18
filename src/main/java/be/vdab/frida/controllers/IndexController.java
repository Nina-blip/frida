package be.vdab.frida.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/")
class IndexController {
    @GetMapping
    public String index(){
        StringBuilder buffer = new StringBuilder("<!doctype html><html><title>Frituur Frida Home</title><body>");
        int dag = LocalDate.now().getDayOfWeek().getValue();
        if (dag == 1 || dag == 4){
            buffer.append("Gesloten vandaag");
        } else {
            buffer.append("Geopend vandaag");
        }
        buffer.append("</body></html>");
        return buffer.toString();
    }
}
