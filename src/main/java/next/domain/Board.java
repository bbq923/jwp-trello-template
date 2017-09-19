package next.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NEXT on 2017. 8. 23..
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;


    private String creator;

    private String created;

    @NonNull
    private String name;

    public Board(String creator, String name) {
        this.creator = creator;
        this.name = name;
    }

    public Board(String name) {
        this.name = name;
    }

    public Boolean matchCreator(User user) {
        return this.creator.equals(user);
    }
}
