package next.web;

import next.dao.BoardRepository;
import next.dao.UserRepository;
import next.domain.Board;
import next.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


/**
 * Created by NEXT on 2017. 8. 31..
 */
@Service
public class BoardService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;

    public void deleteBoard(User user, Long boardId) {
        Board board = boardRepository.findOne(boardId);
        if (board.matchCreator(user)) {
            boardRepository.delete(boardId);
        } else {
            throw new UnauthorizedUserException("user not match");
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = IOException.class, noRollbackFor = NullPointerException.class)
    public Board createBoard(User user, String title) { // Transactional 설정되어있다고 해도 모든 Exception에 대해서 무조건 Rollback 되지는 않는다
                                                        // Runtime Exception에 대해서만 rollback이 이루어진다. Compiiletime Exception은 rollback 안됨
                                                        // rollback 하고 싶으면 따로 rollbackFor 로 지정해 줘야 함
        Board board = new Board(title);

        board.setCreator(user.getEmail());

        return boardRepository.save(board);
    }
}
