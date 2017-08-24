package next.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by NEXT on 2017. 8. 10..
 */
@Controller
public class BoardController {
    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @GetMapping("/boards")
    public String boards() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.debug("logged user : {}", auth);
        return "boards";
    }

    @GetMapping("/board")
    public String board() {
        return "board";
    }
}
