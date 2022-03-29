package academy.mindswap.services;

import academy.mindswap.commands.ReviewDto;
import academy.mindswap.controllers.ReviewController;
import academy.mindswap.persistence.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewConverter reviewConverter;

    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewConverter::convertToDto)
                .collect(java.util.stream.Collectors.toList());
    }

    public void deleteAllReviews() {
        reviewRepository.deleteAll();
    }

    public ReviewDto createReviewByMovieId(ReviewDto reviewDto) {
       return reviewRepository.save(reviewConverter.convertToEntity(reviewDto));
    }

    public ReviewDto getReviewByMovieId(Integer movieId) {
        return reviewConverter.convertToDto(reviewRepository.getReviewByMovieId(movieId));
    }

    public ReviewDto getReviewById(Integer reviewId) {
        return reviewConverter.convertToDto(reviewRepository.getReviewById(reviewId));
    }

    public void deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }


}
