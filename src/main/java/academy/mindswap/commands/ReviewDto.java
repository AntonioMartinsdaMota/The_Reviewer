package academy.mindswap.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private Integer reviewId;

    private String userName;

    @NotBlank( message= "Movie Name cannot be empty")
    private String movieName;

    @NotBlank( message= "Description cannot be empty")
    private String description;

    @NotNull
    @Max(5)
    @Min(1)
    private Float localRating;
}
