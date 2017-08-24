package next.web;

import next.domain.Card;
import next.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by NEXT on 2017. 8. 22..
 */
public class ApiCardControllerTest {

    @Before
    public void setup() {
    }

    @Test
    public void delete() {
        User user = new User("bbq", "bbq@email.com", "1234");
        Card card = new Card(user, "test content");

        card.delete(user);
        assertEquals(true, card.isDeleted());
    }

    @Test
    public void cannotDelete() {
        User user = new User("bbq", "bbq@email.com", "1234");
        User user2 = new User("bbq2", "bbq2@email.com", "1234");
        Card card = new Card(user, "test content");

        card.delete(user2);
        assertEquals(false, card.isDeleted());

    }
}
