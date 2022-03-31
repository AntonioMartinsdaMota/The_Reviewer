package academy.mindswap.converters;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.persistence.models.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    public ReviewDto convertToDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setReviewId(review.getReviewId());
        dto.setUserName(review.getUser().getUsername());
        dto.setMovieName(review.getMovie().getOriginalTitle());
        dto.setDescription(review.getDescription());
        dto.setLocalRating(review.getLocalRating());
        return dto;
    }

    public Review convertToEntity(ReviewDto reviewDto) {
         Review entity = new Review();
        entity.setDescription(reviewDto.getDescription());
        entity.setLocalRating(reviewDto.getLocalRating());
        return entity;
    }
}
