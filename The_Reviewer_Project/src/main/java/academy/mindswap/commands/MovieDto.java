package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MovieDto {

    private Integer movieID;

   /* @NotBlank( message= "ImdbID cannot be empty")
    private String imDbId;*/

    private String originalTitle;

    private String portugueseTitle;

    private Integer year;

    private String type;

    private float IMDBRating;

    private Integer rottenTomatoesRating;

    private float localRating;

    private String directors;

    private String cast;
}
