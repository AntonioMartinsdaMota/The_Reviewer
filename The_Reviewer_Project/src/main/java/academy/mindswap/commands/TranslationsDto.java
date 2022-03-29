package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TranslationsDto {

    @NotBlank
    private String iso_3166_1;

    @NotBlank
    private String iso_639_1;

    @NotBlank
    private List<DataDto> data;
}
