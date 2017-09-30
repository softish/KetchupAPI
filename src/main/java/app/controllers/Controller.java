package app.controllers;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class Controller {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Greetings!";
    }
}
