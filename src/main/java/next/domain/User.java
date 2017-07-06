package next.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @NonNull
    private String userId;

    @NonNull
    private String password;

    @NonNull
    private String email;

}
