package next.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by NEXT on 2017. 8. 1..
 */
@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private long id;

    @NonNull
    private String role;
}
