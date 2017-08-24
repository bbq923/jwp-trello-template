package next.web;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import next.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

/**
 * Created by NEXT on 2017. 6. 29..
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiUserControllerTest {
    private Logger log = LoggerFactory.getLogger(ApiUserControllerTest.class);

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setup() {
        log.debug("local server port : {}", serverPort);
        RestAssured.port = serverPort;
    }

//    @Test
//    public void create() throws Exception {
//        User user = new User("userId", "password", "javajigi@slipp.net");
//
//        String body = given()
//            .contentType(ContentType.JSON)
//            .body(user)
//        .when()
//                .post("/api/users")
//        .then()
//            .statusCode(HttpStatus.CREATED.value())
//            .extract().asString();
//
//        log.debug("body : {}", body);
//    }
}
