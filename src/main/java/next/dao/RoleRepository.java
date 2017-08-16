package next.dao;

import next.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by NEXT on 2017. 8. 1..
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
