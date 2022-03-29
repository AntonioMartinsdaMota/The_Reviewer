package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PersonDto {
    @NotBlank( message= "Name cannot be empty")
    private String name;
}
