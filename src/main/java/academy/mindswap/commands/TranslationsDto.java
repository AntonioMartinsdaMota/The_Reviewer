package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TranslationsDto {

    private String iso_3166_1;

    private String iso_639_1;

    private DataDto data;
}
