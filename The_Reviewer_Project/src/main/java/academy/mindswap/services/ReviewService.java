package academy.mindswap.services;

import academy.mindswap.commands.ReviewDto;
import academy.mindswap.converters.ReviewConverter;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.ReviewRepository;
import academy.mindswap.persistence.repositories.UserRepository;
import academy.mindswap.exceptions.notFoundExceptions.CookieNotFoundException;
import academy.mindswap.exceptions.notFoundExceptions.MovieNotFoundException;
import academy.mindswap.exceptions.notFoundExceptions.ReviewNotFoundException;
import academy.mindswap.exceptions.otherExceptions.NotEnoughPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewConverter reviewConverter;

    @Autowired
    private IMDBService imdbService;

    @Autowired
    private MovieDBService movieDBService;

    @Autowired
    private CookiesService cookiesService;

    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewConverter::convertToDto)
                .collect(java.util.stream.Collectors.toList());
    }

    public void deleteAllReviews() {
        reviewRepository.deleteAll();
    }

    public ReviewDto createReviewByMovieId(ReviewDto reviewDto, HttpServletRequest request)
            throws CookieNotFoundException {

        Integer userId = cookiesService.getIdFromCookie(request);

        Optional<User> user = userRepository.findById(userId);

        Optional<Movie> movieOpt = movieRepository.findByOriginalTitle(reviewDto.getMovieName());
        Review newReview = reviewConverter.convertToEntity(reviewDto);
        newReview.setUser(user.get());

        if(movieOpt.isPresent()) {
            newReview.setMovie(movieOpt.get());
            return reviewConverter.convertToDto(reviewRepository.save(newReview));
        }

        Movie newIMDBMovie = imdbService.createMovieFromIMDB(reviewDto.getMovieName());
        Movie newMovieDBMovie = movieDBService.createMovieFromMovieDB(newIMDBMovie.getOriginalTitle());

        newIMDBMovie.setPortugueseTitle(newMovieDBMovie.getPortugueseTitle());

        newReview.setMovie(movieRepository.save(newIMDBMovie));
        return reviewConverter.convertToDto(reviewRepository.save(newReview));
    }


    public ReviewDto getReviewByMovieId(Integer movieId){
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (movieOpt.isEmpty()){
            throw new MovieNotFoundException();
        }
        return reviewConverter.convertToDto(reviewRepository.findByMovie(movieOpt.get()));
    }

    public ReviewDto getReviewById(Integer reviewId){

        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()){
            throw new ReviewNotFoundException();
        }
        return reviewConverter.convertToDto(reviewOpt.get());
    }

    public void deleteReview(Integer reviewId, HttpServletRequest request){

        Integer userId = cookiesService.getIdFromCookie(request);
        Optional<User> user = userRepository.findById(userId);

        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()){
            throw new ReviewNotFoundException();
        }

        if(!user.get().getUserId().equals(reviewOpt.get().getUser().getUserId()) /*OR ADMIN*/) {
            throw new NotEnoughPermissionsException();
        }

        reviewRepository.deleteById(reviewId);
    }


}
