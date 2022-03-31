package academy.mindswap.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="reviews")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Integer reviewId;

    @ManyToOne
    private User user;
    @ManyToOne
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
