package clone.colley.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column
    private String profileUrl;

    @Column
    private String introduce;

    @OneToMany(mappedBy = "user")
    private List<Posts> postList;


    public User(String username, String password, String nickname) {

        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

}
