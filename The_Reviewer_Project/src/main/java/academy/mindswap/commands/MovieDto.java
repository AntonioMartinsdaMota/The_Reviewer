package academy.mindswap.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {

    private Integer movieID;

    private String originalTitle;

    private String portugueseTitle;

    private Integer year;

    private float IMDBRating;

    private Integer rottenTomatoesRating;

    private float localRating;

    private String directors;

    private String actors;

    private Integer numberOfReviews;
}
