package academy.mindswap.converters;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.persistence.models.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ReviewDto convertToDto(Review review) {
        return modelMapper.map(review, ReviewDto.class);
        /*ReviewDto dto = new ReviewDto();
        dto.setReviewId(review.getReviewId());
        dto.setUserName(review.getUser().getUsername());
        dto.setMovieName(review.getMovie().getOriginalTitle());
        dto.setDescription(review.getDescription());
        dto.setLocalRating(review.getLocalRating());
        return dto;*/
    }

    public Review convertToEntity(ReviewDto reviewDto) {
        return modelMapper.map(reviewDto, Review.class);
      /* Review entity = new Review();
        entity.setDescription(reviewDto.getDescription());
        entity.setLocalRating(reviewDto.getLocalRating());
        return entity;*/
    }
}
