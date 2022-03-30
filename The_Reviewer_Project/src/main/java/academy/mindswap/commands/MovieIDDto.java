package academy.mindswap.commands;

import lombok.Data;

import java.util.List;


@Data
public class MovieIDDto {

    private List<ResultsDto> results;
}
