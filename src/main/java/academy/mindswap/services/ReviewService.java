package academy.mindswap.services;

import academy.mindswap.commands.ReviewDto;
import academy.mindswap.converters.ReviewConverter;
import academy.mindswap.exceptions.badRequestExceptions.RatingOutOfRangeException;
import academy.mindswap.exceptions.notFoundExceptions.UserNotFoundException;
import academy.mindswap.exceptions.otherExceptions.ReviewAlreadyExistsException;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.ReviewRepository;
import academy.mindswap.persistence.repositories.UserRepository;
import academy.mindswap.exceptions.notFoundExceptions.MovieNotFoundException;
import academy.mindswap.exceptions.notFoundExceptions.ReviewNotFoundException;
import academy.mindswap.exceptions.otherExceptions.NotEnoughPermissionsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {


    private final ReviewRepository reviewRepository;


    private final MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;


    private final ReviewConverter reviewConverter;

    @Autowired
    private IMDBService imdbService;

    @Autowired
    private MovieDBService movieDBService;

    @Autowired
    private TokenService tokenService;

    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewConverter::convertToDto)
                .collect(java.util.stream.Collectors.toList());
    }

    public void deleteAllReviews() {
        reviewRepository.deleteAll();
    }

    public ReviewDto createReviewByMovieId(ReviewDto reviewDto, HttpServletRequest request) {

        if (reviewDto.getLocalRating() < 1 || reviewDto.getLocalRating() > 5){
            throw new RatingOutOfRangeException();
        }

        String email = tokenService.getEmailFromToken(request);

        Optional<User> user = userRepository.findByEmail(email);

        Optional<Movie> movieOpt = movieRepository.findByOriginalTitle(reviewDto.getMovieName().toUpperCase());
        Review newReview = reviewConverter.convertToEntity(reviewDto);
        newReview.setUser(user.get());

        if(movieOpt.isPresent()) {
            if(reviewRepository.findByUserAndMovie(user.get(), movieOpt.get()).isPresent()){
                throw new ReviewAlreadyExistsException();
            }

            newReview.setMovie(movieOpt.get());

        }else {

            Movie newIMDBMovie = imdbService.createMovieFromIMDB(reviewDto.getMovieName());
            Movie newMovieDBMovie = movieDBService.createMovieFromMovieDB(newIMDBMovie.getOriginalTitle());

            if (newMovieDBMovie.getPortugueseTitle().equalsIgnoreCase("") || newMovieDBMovie.getPortugueseTitle() == null) {
                newMovieDBMovie.setPortugueseTitle("");
            }

            newIMDBMovie.setPortugueseTitle(newMovieDBMovie.getPortugueseTitle());
            newIMDBMovie.setLocalRating(newReview.getLocalRating());

            Movie newMovie = movieRepository.save(newIMDBMovie);
            newReview.setMovie(newMovie);
        }


        Review savedReview = reviewRepository.save(newReview);

        Movie movie = movieRepository.findById(savedReview.getMovie().getMovieId()).get();
        List<Review> movieReviews = reviewRepository.findByMovie(movie);

        float movieLocalRating = (float) movieReviews.stream()
                .mapToDouble(Review::getLocalRating).average().orElse(0);

        movie.setLocalRating(movieLocalRating);
        movieRepository.save(movie);

        return reviewConverter.convertToDto(newReview);
    }

    public List<ReviewDto> getReviewByMovieId(Integer movieId){
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (movieOpt.isEmpty()){
            throw new MovieNotFoundException();
        }
        return reviewRepository.findByMovie(movieOpt.get()).stream()
                .map(r -> reviewConverter.convertToDto(r)).collect(Collectors.toList());
    }

    public ReviewDto getReviewById(Integer reviewId){

        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()){
            throw new ReviewNotFoundException();
        }
        return reviewConverter.convertToDto(reviewOpt.get());
    }

    public void deleteReview(Integer reviewId, HttpServletRequest request){

        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()){
            throw new ReviewNotFoundException();
        }

        Integer movieID = reviewOpt.get().getMovie().getMovieId();
        reviewRepository.deleteById(reviewId);
        Movie movie = movieRepository.findById(movieID).get();

        float movieLocalRating = (float) movie.getReviews().stream()
                .mapToDouble(Review::getLocalRating).average().orElse(0);
        movie.setLocalRating(movieLocalRating);
        movieRepository.save(movie);
    }

    public void deleteOwnReview(Integer movieId, HttpServletRequest request) {

        String userEmail = tokenService.getEmailFromToken(request);
        User user = userRepository.findByEmail(userEmail).get();
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (movieOpt.isEmpty()){
            throw new MovieNotFoundException();
        }

        Optional<Review> reviewOpt = reviewRepository.findByUserAndMovie(user, movieOpt.get());
        if (reviewOpt.isEmpty()){
            throw new ReviewNotFoundException();
        }

        Integer movieID = reviewOpt.get().getMovie().getMovieId();
        reviewRepository.deleteById(reviewOpt.get().getReviewId());
        Movie movie = movieRepository.findById(movieID).get();

        float movieLocalRating = (float) movie.getReviews().stream()
                .mapToDouble(Review::getLocalRating).average().orElse(0);
        movie.setLocalRating(movieLocalRating);
        movieRepository.save(movie);

    }

    public ReviewDto updateReview(ReviewDto reviewDto, HttpServletRequest request, Integer movieID) {
        String userEmail = tokenService.getEmailFromToken(request);
        User user = userRepository.findByEmail(userEmail).get();
        Optional<Movie> movieOpt = movieRepository.findById(movieID);
        if (movieOpt.isEmpty()){
            throw new MovieNotFoundException();
        }
        Movie movie = movieOpt.get();
        Optional<Review> reviewOpt = reviewRepository.findByUserAndMovie(user, movieOpt.get());
        if (reviewOpt.isEmpty()){
            throw new ReviewNotFoundException();
        }
        Review review = reviewOpt.get();
        if(reviewDto.getLocalRating() != null) {
            review.setLocalRating(reviewDto.getLocalRating());
        }
        if(reviewDto.getDescription() != null) {
            review.setDescription(reviewDto.getDescription());
        }

        Review newReview = reviewRepository.save(review);

        Movie reviewMovie = newReview.getMovie();
        float movieLocalRating = (float) movie.getReviews().stream()
                .mapToDouble(Review::getLocalRating).average().orElse(0);
        movie.setLocalRating(movieLocalRating);
        movieRepository.save(movie);

        return reviewConverter.convertToDto(newReview);
    }
}
