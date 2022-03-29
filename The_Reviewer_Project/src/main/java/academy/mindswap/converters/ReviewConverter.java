package academy.mindswap.converters;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.persistence.models.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ReviewDto convertToDto(Review review) {
        return modelMapper.map(review, ReviewDto.class);
    }

    public Review convertToEntity(ReviewDto reviewDto) {
        return modelMapper.map(reviewDto, Review.class);
    }
}
