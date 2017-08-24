package next.dao;

import next.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by NEXT on 2017. 8. 23..
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Override
    <S extends Board> List<S> save(Iterable<S> board);
}
