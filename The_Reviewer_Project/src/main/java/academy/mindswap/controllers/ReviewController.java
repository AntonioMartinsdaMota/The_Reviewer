package academy.mindswap.controllers;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/reviews/{movieId}")
    public ResponseEntity<ReviewDto> getReviewByMovieId(@PathVariable Integer movieId) {
        ReviewDto reviewDto = reviewService.getReviewByMovieId(movieId);
        return ResponseEntity.ok(reviewDto);
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Integer reviewId) {
        ReviewDto reviewDto = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(reviewDto);
    }

    /**
     * Posters
     */

    @PostMapping("/review")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto reviewDto1 = reviewService.createReviewByMovieId(reviewDto);
      return  ResponseEntity.ok(reviewDto1);
    }


    /**
     * Delete
     */


    @DeleteMapping("/review/{reviewId}")
    public void deleteReviewById(@PathVariable Integer reviewId) {
         reviewService.deleteReview(reviewId);
    }

    @DeleteMapping("/reviews")
    public void deleteAllReviews() {
        reviewService.deleteAllReviews();
    }
}




