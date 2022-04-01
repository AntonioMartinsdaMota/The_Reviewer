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
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Getters
     */

    @GetMapping("/searchid/{id}")//ALL
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.getUserById(id).get();
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @GetMapping("/allusers")//ALL
    public  ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("/{id}/reviews")//ALL
    public ResponseEntity<List<ReviewDto>> getUserReviews(@PathVariable Integer id) {
        List<ReviewDto> reviews = userService.getReviewsByUserId(id);
        return ResponseEntity.ok(reviews);
    }

    /**
     * Posters
     */

    @PostMapping("/create")//NOT AUTH
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Updaters
     */

    @PutMapping("/updatesettings")//ALL - Precisa de cookie
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request){
        UserDto user = userService.updateUser(userDto, request);
        return ResponseEntity.ok(user);
    }

    /**
     * Deleters
     */

    @DeleteMapping("/deletebyid/{id}")//ADMIN
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
       userService.deleteUserByUserId(id);
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteall")//OWNER
    public ResponseEntity<?> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok().build();
    }

}














