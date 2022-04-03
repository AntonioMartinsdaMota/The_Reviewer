package academy.mindswap.unit;

import academy.mindswap.commands.ReviewDto;
import academy.mindswap.controllers.ReviewController;
import academy.mindswap.converters.ReviewConverter;
import academy.mindswap.exceptions.badRequestExceptions.InvalidAssertAuthoritiesException;
import academy.mindswap.exceptions.notFoundExceptions.MovieNotFoundException;
import academy.mindswap.mockdata.MockedData;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.ReviewRepository;
import academy.mindswap.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewConverter reviewConverter;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    private void setup() {
        modelMapper = new ModelMapper();
        reviewConverter = new ReviewConverter(modelMapper);
        reviewService = new ReviewService(reviewRepository,movieRepository, reviewConverter);
    }

    @Test
    public void test_getAllReviews(){
        //given
        List<Review> reviewList = MockedData.getMockedReviewList();
        when(reviewRepository.findAll()).thenReturn(reviewList);

        //when
        List<ReviewDto> response = reviewService.getAllReviews();
        List<ReviewDto> expected = MockedData.getMockedReviewListDto(reviewList);

        //then
        assert(expected.equals(response));

    }

    @Test
    public void test_getReviewById(){
        //given
        Review review = MockedData.getMockedReview();
        when(reviewRepository.findById(review.getReviewId())).thenReturn(Optional.of(review));

        //when
        ReviewDto response = reviewService.getReviewById(review.getReviewId());
        ReviewDto expected = MockedData.getMockedReviewDto(review);

        //then
        assertEquals(expected, response);
    }

    @Test
    public void test_getReviewByMovieId_Should_Return_MovieNotFoundException(){
        //given
        List<Review> reviewList = MockedData.getMockedReviewList();
        Movie movie = MockedData.getMockedMovie();
        when(reviewRepository.findByMovie(movie)).thenReturn(reviewList);

        //then
        assertThrows(MovieNotFoundException.class, () -> reviewService.getReviewByMovieId(movie.getMovieId()) );
    }





}
