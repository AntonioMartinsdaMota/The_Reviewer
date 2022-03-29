package academy.mindswap.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;


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
    private User user;
    @ManyToOne
    private Movie movie;

    @Column(nullable = false)
    private String description;

    @Column
    private Integer localRating;

}
