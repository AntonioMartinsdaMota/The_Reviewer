package academy.mindswap.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Integer userId;
    @Column
    @Size(min = 3, max = 15)
    private String username;
    @Column
    @Email
    private String email;
    @Column
    private String password;
    @OneToMany (
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "user"
    )
    private Set<Review> reviews;
    @Column
    private Integer securityRoleId;


}
