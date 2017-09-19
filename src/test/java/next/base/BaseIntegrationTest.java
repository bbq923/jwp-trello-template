package next.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import next.dao.UserRepository;
import next.domain.User;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

/**
 * Created by NEXT on 2017. 8. 31..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BaseIntegrationTest {
    @Value("${local.server.port}")
    protected int serverPort;

    @Autowired
    private UserRepository userRepository;

    protected User loginUser;

    @Before
    public void setup() {
        RestAssured.port = serverPort;
        loginUser = userRepository.findByEmail("bbq@naver.com");
    }

    protected RequestSpecification given_auth_json() {
        return given()
                .auth().preemptive().basic(loginUser.getEmail(), "1234") // DB 에 raw password를 저장하지 않아 하드코딩함.
                .contentType(ContentType.JSON);
    }

    protected RequestSpecification given_auth_json(String email) {
        User user = userRepository.findByEmail(email);
        return given()
                .auth().preemptive().basic(user.getEmail(), "1234")
                .contentType(ContentType.JSON);
    }
}
