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

    @NotBlank( message= "Title cannot be empty")
    private String portugueseTitle;

    @NotBlank( message= "Year cannot be empty")
    private Integer year;

    private float imDb;

    private Integer rottenTomatoes;

    @NotBlank( message= "Director cannot be empty")
    private String directors;

    @NotBlank( message= "Cast cannot be empty")
    private String cast;
}
