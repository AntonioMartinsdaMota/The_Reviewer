package academy.mindswap.services;

import academy.mindswap.commands.MovieFullCastDto;
import academy.mindswap.commands.ReviewDto;
import academy.mindswap.controllers.ReviewController;
import academy.mindswap.converters.ReviewConverter;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.ReviewRepository;
import academy.mindswap.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

        Optional<Movie> movieOpt = movieRepository.findByOriginalTitle(reviewDto.getMovieName());
        Review newReview = reviewConverter.convertToEntity(reviewDto);
        newReview.setUser(userRepository.findByUsername(reviewDto.getUserName()));

        if(movieOpt.isPresent()) {
            newReview.setMovie(movieOpt.get());
            return reviewConverter.convertToDto(reviewRepository.save(newReview));
        }

        Movie newIMDBMovie = imdbService.createMovieFromIMDB(reviewDto.getMovieName());
        Movie newMovieDBMovie = movieDBService.createMovieFromIMDB(reviewDto.getMovieName());

        newIMDBMovie.setPortugueseTitle(newMovieDBMovie.getPortugueseTitle());

        newReview.setMovie(movieRepository.save(newIMDBMovie));
        return reviewConverter.convertToDto(reviewRepository.save(newReview));
    }


    public ReviewDto getReviewByMovieId(Integer movieId) throws MovieNotFoundException{
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (movieOpt.isEmpty()){
            throw new MovieNotFoundException();
        }
        return reviewConverter.convertToDto(reviewRepository.findByMovie(movieOpt.get()));
    }

    public ReviewDto getReviewById(Integer reviewId) throws ReviewNotFoundException {

        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()){
            throw new ReviewNotFoundException();
        }
        return reviewConverter.convertToDto(reviewOpt.get());
    }

    public void deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }


}
