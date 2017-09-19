package next.web;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import next.base.BaseIntegrationTest;
import next.domain.Board;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;


/**
 * Created by NEXT on 2017. 8. 24..
 */
public class ApiBoardControllerTest extends BaseIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(ApiBoardControllerTest.class);

    @Test
    public void make() {
        Board board = new Board("new board");

        String body = given_auth_json()
                .body(board)
            .when()
                .post("/api/board")
            .then()
                .statusCode(HttpStatus.CREATED.value())
                    .extract().asString();

        log.debug("body : {}", body);

    }

    @Test
    public void makdAndRetrieve() {
        String body = given_auth_json()
            .when()
                .get("/api/board")
            .then()
                .statusCode(HttpStatus.OK.value())
                    .extract().asString();

        log.debug("body : {}", body);
    }

    @Test
    public void deleteSuccessWhenUserMatches() {
        given_auth_json()
            .when()
                .delete("/api/board/3")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test(expected = UnauthorizedUserException.class) // http 를 통해 클라이언트 서버간 통신을 하고 있는 것처럼 동작하기 때문에 서버의 Exception을
                                                      // 클라이언트에서 그대로 쓸 수 없다 네트워크 상으로 흘러다니는 놈들은 JSON 형태
    public void deleteFailWhenUserNotMatches() {
        given_auth_json("asdf@asdf.com")
            .when()
                .delete("/api/board/1");
    }
}
