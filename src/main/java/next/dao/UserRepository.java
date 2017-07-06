package next.dao;

import next.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by NEXT on 2017. 7. 6..
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    <S extends User> List<S> save(Iterable<S> user);
}
