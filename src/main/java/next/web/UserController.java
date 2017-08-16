package next.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by NEXT on 2017. 7. 6..
 */

@Controller
public class UserController {

    @GetMapping("/loginForm")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/form")
    public String signupPage() {
        return "signUp";
    }

    @GetMapping("/admin/test")
    public String adminPage() {
        return "login";
    }

    @GetMapping("/user/test")
    public String userPage() {
        return "login";
    }
}
