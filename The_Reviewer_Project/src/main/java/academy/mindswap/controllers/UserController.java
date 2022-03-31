package academy.mindswap.controllers;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.models.User;
import academy.mindswap.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @GetMapping("/user/{id}")//ALL
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.getUserById(id).get();
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @GetMapping("/allUsers")//ALL
    public  ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("/user/{id}/reviews")//ALL
    public ResponseEntity<List<ReviewDto>> getUserReviews(@PathVariable Integer id) {
        List<ReviewDto> reviews = userService.getReviewsByUserId(id);
        return ResponseEntity.ok(reviews);
    }


   /* @GetMapping("/user/{id}/review/{title}")
    public ResponseEntity<Review> deleteReviewByMovieTitle(@PathVariable Integer id, @PathVariable String title) {
        return userService.getReviewByMovieTitle(id, title);
    }
*/

    /**
     * Posters
     */

    @PostMapping("/user/create")//NOT AUTH
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /*@PostMapping("/user/{id}/review/new")
    public ResponseEntity<Review> createReview(@PathVariable Integer id, @RequestBody Review review) {
        return userService.createReview(id, review);
    }*/


    /**
     * Updaters
     */

    @PutMapping("/user/settings")//ALL - Precisa de cookie
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request){
        UserDto user = userService.updateUser(userDto, request);
        return ResponseEntity.ok(user);
    }

    /*@PutMapping("/user/{id}/review/{title}")
    public ResponseEntity<Review> updateReviewByMovieTitle(@PathVariable Integer id, @PathVariable String title, @RequestBody Review review) {
        return userService.updateReviewByMovieTitle(id, title, review);
    }*/

    /**
     * Deleters
     */

    @DeleteMapping("/user/{id}")//ADMIN
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
       userService.deleteUserByUserId(id);
       return ResponseEntity.ok().build();
    }

   /* @DeleteMapping("/user/{username}")
    public ResponseEntity<User> deleteUserByUsername(@PathVariable String username) {
        return userService.deleteUserByUsername(username);
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<User> deleteUserByEmail(@PathVariable String email) {
        return userService.deleteUserByEmail(email);
    }*/

    @DeleteMapping("/users")//OWNER
    public ResponseEntity<?> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok().build();
    }

    /*@DeleteMapping("/user/{id}/review/{reviewId}")
    public ResponseEntity<Review> deleteReview(@PathVariable Integer id, @PathVariable Integer reviewId) {
        return userService.deleteReviewByReviewId(id, reviewId);
    }*/

}














