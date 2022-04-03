package academy.mindswap.controllers;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.exceptions.notFoundExceptions.UserNotFoundException;
import academy.mindswap.persistence.models.Role;
import academy.mindswap.persistence.models.User;
import academy.mindswap.services.TokenService;
import academy.mindswap.services.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

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

    @PutMapping("{userId}/admin")//OWNER
    public ResponseEntity<UserDto> turnAdmin(@PathVariable Integer userId){
        UserDto user = userService.turnAdmin(userId);
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

    @GetMapping("/token/refresh")//NOT AUTH
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenService.refreshToken(request, response);
    }

}














