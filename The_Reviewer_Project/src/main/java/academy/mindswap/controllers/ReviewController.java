package academy.mindswap.controllers;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.exceptions.notFoundExceptions.CookieNotFoundException;
import academy.mindswap.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/reviews")//admin
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/reviews/{movieId}")//ALL
    public ResponseEntity<ReviewDto> getReviewByMovieId(@PathVariable Integer movieId) {
        ReviewDto reviewDto = reviewService.getReviewByMovieId(movieId);
        return ResponseEntity.ok(reviewDto);
    }

    @GetMapping("/review/{reviewId}")//ALL
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Integer reviewId) {
        ReviewDto reviewDto = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(reviewDto);
    }

    /**
     * Posters
     */

    @PostMapping("/review")//ALL
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, HttpServletRequest request)
            throws MovieNotFoundInMovieDBException, CookieNotFoundException {
      return  ResponseEntity.ok(reviewService.createReviewByMovieId(reviewDto, request));
    }


    /**
     * Delete
     */


    @DeleteMapping("/review/{reviewId}")//ALL
    public void deleteReviewById(@PathVariable Integer reviewId, HttpServletRequest request) throws Exception {
         reviewService.deleteReview(reviewId, request);
    }

    @DeleteMapping("/reviews")//ADMIN
    public void deleteAllReviews() {
        reviewService.deleteAllReviews();
    }
}




