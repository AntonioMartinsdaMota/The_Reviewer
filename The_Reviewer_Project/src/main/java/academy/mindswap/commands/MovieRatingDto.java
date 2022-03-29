package academy.mindswap.commands;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
public class MovieRatingDto {

    @NotBlank( message= "ImdbID cannot be empty")
    private String imDbId;

    @NotBlank( message= "Title cannot be empty")
    private String title;

    @NotBlank( message= "Type cannot be empty")
    private String type;

    @NotBlank( message= "Year cannot be empty")
    private Integer year;

    private float imDb;

    private Integer rottenTomatoes;
}
