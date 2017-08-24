package next.web;

import next.dao.BoardRepository;
import next.domain.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by NEXT on 2017. 8. 23..
 */
@RestController
@RequestMapping("/api/board")
public class ApiBoardController {
    private Logger log = LoggerFactory.getLogger(ApiBoardController.class);

    @Autowired
    BoardRepository boardRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Board create(@RequestBody Board board) {
        log.debug("board created : {}", board);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String creator = auth.getName(); // get logged in username

        board.setCreator(creator);

        log.debug("set creator : {}", board);

        return boardRepository.save(board);
    }
}
