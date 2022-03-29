package academy.mindswap.controllers;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * Getters
     */

    @GetMapping("/reviews")
    public ResponseEntity<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/reviews/{movieId}")
    public ResponseEntity<ReviewDto> getReviewsByMovieId(@PathVariable Integer movieId) {
        return reviewService.getReviewsByMovieId(movieId);
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Integer reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    /**
     * Posters
     */

    @PostMapping("/review/{movieId}")
    public ResponseEntity<ReviewDto> createReviewByMovieId(@RequestBody ReviewDto reviewDto, @PathVariable Integer movieId) {
      return reviewService.createReviewByMovieId(reviewDto, movieId);
    }


    /**
     * Delete
     */


    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<ReviewDto> deleteReviewById(@PathVariable Integer reviewId) {
        return reviewService.deleteReviewById(reviewId);
    }
    @DeleteMapping("/reviews")
    public ResponseEntity<ReviewDto> deleteAllReviews() {
        return reviewService.deleteAllReviews();
    }


}




