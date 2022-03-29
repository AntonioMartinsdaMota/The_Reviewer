package academy.mindswap.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@Table(name="movies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Integer movieId;
    @Column(nullable = false, unique = true, updatable = false)
    private String imDbId;
    @Column(nullable = false, unique = true)
    private String originalTitle;
    @Column(nullable = false, unique = true)
    private String portugueseTitle;
    @Column(nullable = false)
    private String directors;
    @Column(nullable = false)
    private String actors;
    @Column(nullable = false)
    private Integer year;
    @Column
    private String type;
    @Column
    private float IMDBRating;
    @Column
    private Integer rottenTomatoesRating;
    @Column
    private float localRating;
    @OneToMany (
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "user"
    )
    private Set<Review> reviews;

}
