package academy.mindswap.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Table(name="reviews")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(nullable = false)
    private String description;

    @Column
    private Integer localRating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(getUser(), review.getUser()) && Objects.equals(getMovie(), review.getMovie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getMovie());
    }
}
