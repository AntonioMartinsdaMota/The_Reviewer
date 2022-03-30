package academy.mindswap.commands;

import lombok.Data;

import java.util.List;


@Data
public class MovieMovieDBIDDto {

    private Integer page;
    private List<ResultsMovieDBDto> results;
}
