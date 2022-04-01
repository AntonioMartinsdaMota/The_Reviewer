package academy.mindswap.acceptance;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.mockdata.MockedData;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.ReviewRepository;
import academy.mindswap.persistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static academy.mindswap.mockdata.MockedData.getMockedCookie;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewControllerTest {

    @MockBean
    private ReviewRepository reviewRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Test for getting all reviews
     */

    @Test
    public void test_getAllReviews_shouldReturn_Success() {
        //Given

        List<Review> reviewList = MockedData.getMockedReviewList();
        when(reviewRepository.findAll()).thenReturn(reviewList);
        String url = "/api/review/allreviews";

        //When

        ResponseEntity<List<ReviewDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReviewDto>>() {
                }
        );

        //Then

        List<ReviewDto> expected = MockedData.getMockedReviewListDto(reviewList);
        List<ReviewDto> actual = response.getBody();
        assertEquals(expected, actual);

    }

    /**
     * Test for getting all reviews by movie
     */

  /*  @Test
    public void test_getReviewsByMovieId_ShouldReturn_Success() {
        //Given

        Movie movie = MockedData.getMockedMovie();
        Integer movieId = movie.getMovieId();
        when(reviewRepository.findByMovie(movie)).thenReturn(MockedData.getMockedReviewList());

        String url = "/api/review/searchmovie/" + movieId;

        //When

        ResponseEntity<List<ReviewDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReviewDto>>() {}
        );

        //Then

        List<ReviewDto> expected = MockedData.getMockedReviewListDto(MockedData.getMockedReviewList());
        List<ReviewDto> actual = response.getBody();
        assertEquals(expected, actual);

    }*/

    /**
     * Test for getting a review by id
     */

    @Test
    public void test_getReviewById_Should_Return_Success() {
        //Given

        Review review = MockedData.getMockedReview();
        Integer reviewId = review.getReviewId();
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        String url = "/api/review/searchid/" + reviewId;

        //When

        ResponseEntity<ReviewDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                ReviewDto.class
        );

        //Then

        ReviewDto expected = MockedData.getMockedReviewDto(review);
        ReviewDto actual = response.getBody();
        assertEquals(expected, actual);

    }

    /**
     * Test for creating a review
     */

    @Test
    public void test_createReview_Should_Return_NotFound() {
        //Given

        Review review = MockedData.getMockedReview();
        ReviewDto reviewDto = MockedData.getMockedReviewDto(review);
        when(reviewRepository.save(review)).thenReturn(review);

        Cookie cookie = getMockedCookie();
        //HttpHeaders headers = new HttpHeaders();
        //headers.add(HttpHeaders.COOKIE, cookie.getName() + "=" + cookie.getValue());
        String url = "/api/review/create";


        //When

        ResponseEntity<ReviewDto> response = restTemplate.postForEntity(
                url,
                new HttpEntity<>(reviewDto/*headers*/),
                ReviewDto.class
        );

        //Then

        ReviewDto expected = MockedData.getMockedReviewDto(review);
        ReviewDto actual = response.getBody();
       // assertEquals(expected, actual);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


    }

    /**
     * Test for deleting a review
     */

    @Test
    public void test_deleteReview_Should_Return_CookieNotFound() {
        //Given

        Review review = MockedData.getMockedReview();
        Integer reviewId = review.getReviewId();
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        String url = "/api/review/delete/" + reviewId;

        //When

        ResponseEntity<ReviewDto> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                ReviewDto.class
        );

        //Then

        ReviewDto expected = MockedData.getMockedReviewDto(review);
        ReviewDto actual = response.getBody();
       // assertEquals(expected, actual);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
}