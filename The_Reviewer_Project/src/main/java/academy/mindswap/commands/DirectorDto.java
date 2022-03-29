package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class DirectorDto {

    private String job;

    @NotBlank( message= "Persons cannot be empty")
    private List<PersonDto> items;
}
