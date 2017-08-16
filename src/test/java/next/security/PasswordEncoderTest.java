package next.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by NEXT on 2017. 7. 25..
 */
@Slf4j
public class PasswordEncoderTest {
    @Test
    public void bcryptPasswordEncoder() throws Exception {
        BCryptPasswordEncoder bcode = new BCryptPasswordEncoder();

        String encoded1 = bcode.encode("password");
        String encoded2 = bcode.encode("password");

        log.debug("encoded1 : {}", encoded1);
        log.debug("encoded2 : {}", encoded2);

        log.debug("match1 : {}", bcode.matches("password", encoded1));
        log.debug("match2 : {}", bcode.matches("password", encoded2));
    }
}
