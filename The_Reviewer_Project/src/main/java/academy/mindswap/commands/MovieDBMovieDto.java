package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MovieDBMovieDto {
    @NotBlank
    private String id;
}