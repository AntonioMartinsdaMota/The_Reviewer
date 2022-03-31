package academy.mindswap.commands;

import lombok.Data;

import java.util.List;

@Data
public class MovieMovieDBIDDto {

    private List<ResultsMovieDBDto> results;
}
