package next.web;

import next.dao.UserRepository;
import next.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by NEXT on 2017. 6. 29..
 */
@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    private Logger log = LoggerFactory.getLogger(ApiUserController.class);

    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        log.debug("signup request : {}", user);
        return userRepository.save(user);
    }
}
