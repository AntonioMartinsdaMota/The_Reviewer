package academy.mindswap.converters;

import academy.mindswap.commands.ReviewDto;
import academy.mindswap.persistence.models.Review;
import lombok.Data;

@Data
public class ReviewConverter {

    public ReviewDto toDto(Review review){
        return new ReviewDto();
    }

}
