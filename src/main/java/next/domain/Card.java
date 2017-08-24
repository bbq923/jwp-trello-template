package next.domain;

import java.util.Set;

/**
 * Created by NEXT on 2017. 8. 22..
 */
public class Card {
    private User creator;

    private String content;

    private Set<Comment> comments;

    private boolean deleted;

    public Card(User creator, String content) {
        this.creator = creator;
        this.content = content;
    }

    public void delete(User user) {
//        if (deleted) {
//            throw isAlreadyDeletedException;
//        }
        if (user.equals(this.creator)) {
            this.deleted = true;
        }
//        throw BadUserDeleteRequestException;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
}
