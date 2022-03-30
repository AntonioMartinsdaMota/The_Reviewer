package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class MovieIMDBIDDto {

    @NotBlank( message= "Persons cannot be empty")
    private List<ResultsIMDBDto> results;
}
