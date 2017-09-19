package next.web;

import next.dao.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by NEXT on 2017. 8. 10..
 */
@Controller
public class BoardController {
    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/boards")
    public String boards(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.debug("logged user : {}", auth);

        model.addAttribute("boards", boardRepository.findAll());

        return "boards";
    }

    @GetMapping("/board")
    public String board() {
        return "board";
    }
}
