package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DataDto {

    @NotBlank
    private String title;
}
