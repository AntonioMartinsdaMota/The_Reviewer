package academy.mindswap.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.EAGER;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Integer userId;
    @Column
    private String username;
    @Column
    private String email;
    @Column()
    private String password;
    @ManyToMany(fetch= EAGER)
    private Collection<Role> roles = new ArrayList<>();
    @OneToMany (
            cascade = {CascadeType.ALL},
            mappedBy = "user"
    )
    @Column
    private Set<Review> reviews;

    public User() {
        this.reviews = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
