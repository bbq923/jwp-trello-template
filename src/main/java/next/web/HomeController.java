package next.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by NEXT on 2017. 6. 29..
 */

@Controller
public class HomeController {
    private Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("")
    public String home() {
        return "index";
    }
}
