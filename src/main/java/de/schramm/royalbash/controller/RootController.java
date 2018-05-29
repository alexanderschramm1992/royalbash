package de.schramm.royalbash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class RootController {

    @RequestMapping("/api")
    public String swagger() {
        return "redirect:swagger-ui.html";
    }
}
