package academy.mindswap.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Column(unique = true)
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
            mappedBy = "movie"
    )
    @Column
    private Set<Review> reviews;

    public Movie() {
        this.reviews = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", imDbId='" + imDbId + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", portugueseTitle='" + portugueseTitle + '\'' +
                ", directors='" + directors + '\'' +
                ", actors='" + actors + '\'' +
                ", year=" + year +
                ", type='" + type + '\'' +
                ", IMDBRating=" + IMDBRating +
                ", rottenTomatoesRating=" + rottenTomatoesRating +
                ", localRating=" + localRating +
                '}';
    }
}
