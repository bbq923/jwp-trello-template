package next.web;

import next.dao.BoardRepository;
import next.dao.UserRepository;
import next.domain.Board;
import next.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.List;

/**
 * Created by NEXT on 2017. 8. 23..
 */
@RestController
@RequestMapping("/api/board")
public class ApiBoardController {
    private Logger log = LoggerFactory.getLogger(ApiBoardController.class);

    // TODO boardRepository 와 userRepository의 기능을 사용해야 하는 부분을 BoardService로 이동시키기
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardService boardService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Board board, UriComponentsBuilder b) {
        log.debug("board created : {}", board);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String creator = auth.getName(); // get logged in username

        board.setCreator(creator);

        log.debug("set creator : {}", board);

        Long boardId = boardRepository.save(board).getId();

        UriComponents uriComponents =
                b.path("/api/board/" + boardId).buildAndExpand();

//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(uriComponents.toUri());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Board> retrieve() {
        return boardRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(Principal principal, @PathVariable Long id) {
        String email = principal.getName();
        User loginUser = userRepository.findByEmail(email);
        try {
            boardService.deleteBoard(loginUser, id);
        } catch (UnauthorizedUserException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Board getBoard(@PathVariable Long id) {
        return boardRepository.findOne(id);
    }
}
