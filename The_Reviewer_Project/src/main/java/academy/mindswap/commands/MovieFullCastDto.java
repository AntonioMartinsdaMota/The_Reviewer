package academy.mindswap.commands;

import lombok.Data;

import java.util.List;

@Data
public class MovieFullCastDto {


    private List<DirectorDto> directors;
    private List<PersonDto> actors;
}
