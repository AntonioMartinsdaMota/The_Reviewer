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
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * Getters
     */

    @GetMapping("/allreviews")//admin
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/searchmovie/{movieId}")//ALL
    public ResponseEntity<List<ReviewDto>> getReviewsByMovieId(@PathVariable Integer movieId) {
        List<ReviewDto> reviewDto = reviewService.getReviewByMovieId(movieId);
        return ResponseEntity.ok(reviewDto);
    }

    @GetMapping("/searchid/{reviewId}")//ALL
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Integer reviewId) {
        ReviewDto reviewDto = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(reviewDto);
    }

    /**
     * Posters
     */

    @PostMapping("/create")//ALL
    public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto, HttpServletRequest request) {
      return  ResponseEntity.ok(reviewService.createReviewByMovieId(reviewDto, request));
    }


    /**
     * Delete
     */


    @DeleteMapping("/delete/{reviewId}")//ADMIN
    public void deleteReviewById(@PathVariable Integer reviewId, HttpServletRequest request){
         reviewService.deleteReview(reviewId, request);
    }

    @DeleteMapping("/deletemyreview/{movieId}")//ALL
    public void deleteOwnReview(@PathVariable Integer movieId, HttpServletRequest request){
        reviewService.deleteOwnReview(movieId, request);
    }

    @DeleteMapping("/deleteall")//ADMIN
    public void deleteAllReviews() {
        reviewService.deleteAllReviews();
    }
}




