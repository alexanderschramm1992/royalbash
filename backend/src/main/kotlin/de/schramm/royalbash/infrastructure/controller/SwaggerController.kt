package de.schramm.royalbash.infrastructure.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class SwaggerController {

    @RequestMapping("/api")
    fun swagger() = "redirect:swagger-ui.html"
}
