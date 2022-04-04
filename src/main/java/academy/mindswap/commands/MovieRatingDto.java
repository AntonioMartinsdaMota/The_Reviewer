package academy.mindswap.commands;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
public class MovieRatingDto {


    private String imDbId;

    private String title;

    private String type;

    private Integer year;

    private float imDb;

    private Integer rottenTomatoes;
}
