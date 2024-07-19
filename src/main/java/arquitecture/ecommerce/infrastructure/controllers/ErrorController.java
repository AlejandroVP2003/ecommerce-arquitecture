package arquitecture.ecommerce.infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/error")
public class ErrorController {
    
    public String error() {
        return "redirect:/";
    }
    
}
