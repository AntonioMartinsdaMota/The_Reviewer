package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class MovieIMDBIDDto {

    private List<ResultsIMDBDto> results;
}
