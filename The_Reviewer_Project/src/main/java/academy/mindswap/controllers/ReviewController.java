package academy.mindswap.controllers;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.exceptions.notFoundExceptions.CookieNotFoundException;
import academy.mindswap.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public ResponseEntity<List<ReviewDto>> getReviewsByMovieId(@PathVariable Integer movieId) {
        List<ReviewDto> reviewDto = reviewService.getReviewByMovieId(movieId);
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
    public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto, HttpServletRequest request) {
      return  ResponseEntity.ok(reviewService.createReviewByMovieId(reviewDto, request));
    }


    /**
     * Delete
     */


    @DeleteMapping("/review/{reviewId}")//ALL
    public void deleteReviewById(@PathVariable Integer reviewId, HttpServletRequest request){
         reviewService.deleteReview(reviewId, request);
    }

    @DeleteMapping("/reviews")//ADMIN
    public void deleteAllReviews() {
        reviewService.deleteAllReviews();
    }
}




