package doners;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/digital-doner")
public class HomeController {

    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }

}
