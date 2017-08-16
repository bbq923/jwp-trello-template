package next;

import next.dao.RoleRepository;
import next.dao.UserRepository;
import next.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by NEXT on 2017. 7. 27..
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        LOGGER.debug("load email : {}", email);
        next.domain.User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email + "은 존재하지 않는 사용자입니다");
        }

        LOGGER.debug("loaded User : {}", user);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(next.domain.User user, List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPassword(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
        for (Role r : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getRole()));
        }

        return authorities;
    }
}
