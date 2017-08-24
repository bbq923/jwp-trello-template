package next;

import lombok.extern.slf4j.Slf4j;
import next.dao.GithubUserRepository;
import next.domain.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by NEXT on 2017. 8. 10..
 */
@Component
@Slf4j
public class AuthenticationEventListener {
    private final PrincipalExtractor principalExtractor = new FixedPrincipalExtractor();

    @Resource(name = "customUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private GithubUserRepository githubUserRepository;

    @EventListener
    public void handleAuthenticationSuccess(InteractiveAuthenticationSuccessEvent event) {
        if (!(event.getAuthentication() instanceof OAuth2Authentication)) {
            return;
        }

        OAuth2Authentication authentication = (OAuth2Authentication) event.getAuthentication();
        Object principal = authentication.getPrincipal();

        Map<String, Object> map = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        log.debug("authentication details : {}", map);

        getUser(map);
    }

    private void getUser(Map<String, Object> map) {
        String username = principalExtractor.extractPrincipal(map).toString();
        log.debug("loaded username : {}", username);

        UserDetails user;
        try {
            // userDetailsService를 활용해 DB에서 사용자를 조회
            userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            // 새로운 사용자를 등록한다.
            githubUserRepository.save(new GithubUser(username));
        }
//        return user;
    }
}
