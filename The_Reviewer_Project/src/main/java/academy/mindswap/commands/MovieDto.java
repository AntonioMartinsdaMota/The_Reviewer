package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MovieDto {

    private Integer movieID;

   /* @NotBlank( message= "ImdbID cannot be empty")
    private String imDbId;*/

    @NotBlank( message= "Title cannot be empty")
    private String originalTitle;

    @NotBlank( message= "Portuguese Title cannot be empty")
    private String portugueseTitle;

    @NotBlank( message= "Year cannot be empty")
    private Integer year;

    @NotBlank( message= "Type cannot be empty")
    private String type;

    private float IMDBRating;

    private Integer rottenTomatoesRating;

    private float localRating;

    @NotBlank( message= "Director cannot be empty")
    private String directors;

    @NotBlank( message= "Cast cannot be empty")
    private String cast;
}
