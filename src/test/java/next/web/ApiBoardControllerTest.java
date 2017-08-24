package next.web;

import next.domain.Board;
import next.domain.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by NEXT on 2017. 8. 24..
 */
public class ApiBoardControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ApiBoardControllerTest.class);

    @Test
    public void make() {
        User user = new User("bbq", "bbq@email.com", "1234");
        Board board = new Board(user.getUserName(), "new board");

        log.debug("board : {}", board);
        assertTrue(user.getUserName().equals(board.getCreator()));
    }
}
