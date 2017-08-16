package next.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by NEXT on 2017. 8. 10..
 */
@Controller
public class BoardController {
    @GetMapping("/boards")
    public String boards() {
        return "boards";
    }

    @GetMapping("/board")
    public String board() {
        return "board";
    }
}
