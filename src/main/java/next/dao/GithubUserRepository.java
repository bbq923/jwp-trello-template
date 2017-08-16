package next.dao;

import next.domain.GithubUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by NEXT on 2017. 8. 10..
 */
public interface GithubUserRepository extends JpaRepository<GithubUser, Long> {
    @Override
    <S extends GithubUser> List<S> save(Iterable<S> user);
    GithubUser findByEmail(String email);
}
