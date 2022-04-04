package academy.mindswap.converters;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.persistence.models.Review;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewConverter {


    private final ModelMapper modelMapper;

    public ReviewDto convertToDto(Review review) {
        ReviewDto dto = modelMapper.map(review, ReviewDto.class);
        dto.setUserName(review.getUser().getUsername());
        dto.setMovieName(review.getMovie().getOriginalTitle());
        return dto;
    }

    public Review convertToEntity(ReviewDto reviewDto) {
        Review review = modelMapper.map(reviewDto, Review.class);
        review.setReviewId(null);
        return review;
    }
}
