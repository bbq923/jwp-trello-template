package next.web;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

/**
 * Created by NEXT on 2017. 8. 29..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BoardControllerTest {
    private static final Logger log = LoggerFactory.getLogger(BoardControllerTest.class);

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setup() {
        log.debug("local server port : {}", serverPort);
        RestAssured.port = serverPort;
    }

    @Test
    public void retrieve() {
        String body = given()
                .auth().preemptive().basic("bbq@naver.com","1234")
            .when()
                .get("/boards")
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().asString();


        log.debug("body : {}", body);
    }
}
