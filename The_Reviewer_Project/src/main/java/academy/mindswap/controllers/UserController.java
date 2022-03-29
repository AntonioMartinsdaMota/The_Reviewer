package academy.mindswap.controllers;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.models.User;
import academy.mindswap.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Getters
     */

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/allUsers")
    public  ResponseEntity<List<UserDto>> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}/reviews")
    public ResponseEntity<List<ReviewDto>> getUserReviews(@PathVariable Integer id) {
        return userService.getUserReviews(id);
    }

    @GetMapping("/user/{id}/review/{title}")
    public ResponseEntity<ReviewDto> deleteReviewByMovieTitle(@PathVariable Integer id, @PathVariable String title) {
        return userService.getReviewByMovieTitle(id, title);
    }


    /**
     * Posters
     */

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    /*@PostMapping("/user/{id}/review/new")
    public ResponseEntity<Review> createReview(@PathVariable Integer id, @RequestBody Review review) {
        return userService.createReview(id, review);
    }*/


    /**
     * Updaters
     */

    @PutMapping("/user/settings")
    public ResponseEntity<UserDto> updateUserByUsername(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    /*@PutMapping("/user/{id}/review/{title}")
    public ResponseEntity<Review> updateReviewByMovieTitle(@PathVariable Integer id, @PathVariable String title, @RequestBody Review review) {
        return userService.updateReviewByMovieTitle(id, title, review);
    }*/

    /**
     * Deleters
     */

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

   /* @DeleteMapping("/user/{username}")
    public ResponseEntity<User> deleteUserByUsername(@PathVariable String username) {
        return userService.deleteUserByUsername(username);
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<User> deleteUserByEmail(@PathVariable String email) {
        return userService.deleteUserByEmail(email);
    }*/

    @DeleteMapping("/users")
    public ResponseEntity<UserDto> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

    /*@DeleteMapping("/user/{id}/review/{reviewId}")
    public ResponseEntity<Review> deleteReview(@PathVariable Integer id, @PathVariable Integer reviewId) {
        return userService.deleteReviewByReviewId(id, reviewId);
    }*/

}














