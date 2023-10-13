package preonboarding.wanted.backend.data.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.wanted.backend.data.apply.Apply;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private final List<Apply> applies = new ArrayList<>();

    private String name;

    public User(String name) {
        this.name = name;
    }
}
