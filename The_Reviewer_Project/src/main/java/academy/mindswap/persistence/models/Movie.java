package academy.mindswap.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
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
    @Column
    private String originalTitle;
    @Column
    private String portugueseTitle;
    @Column
    private String director;
    @Column
    private String type;
    @Column
    private Float ratingIMDB;
    @Column
    private Integer ratingRottenTomatoes;
    @OneToMany (
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "user"
    )
    private Set<Review> reviews;

}
