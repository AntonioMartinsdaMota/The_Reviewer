package academy.mindswap.services;

import academy.mindswap.commands.MovieDto;
import academy.mindswap.commands.ReviewDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.converters.MovieConverter;
import academy.mindswap.converters.ReviewConverter;
import academy.mindswap.converters.UserConverter;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.UserRepository;
import academy.mindswap.persistence.repositories.exceptions.InvalidUserId;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userconverter;

    @Autowired
    private ReviewConverter reviewConverter;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieConverter;


    /*public User login(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }

    public User validate(String email) {
        return userRepository.findByEmail(email);
    }*/

    public Optional<UserDto> getUserById(int id) {
        if(id < 0) {
            LOGGER.log(Level.WARN, "Unknown user: " + id);
            throw new InvalidUserId(Integer.toString(id));
        }
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new UsernameNotFoundException((Integer.toString(id)));
        }
        return user.map(userconverter::toDto);
    }

    public List<ReviewDto> getUserReviews(int id){
        if(id < 0) {
            LOGGER.log(Level.WARN, "Unknown user: " + id);
            throw new InvalidUserId(Integer.toString(id));
        }
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("");
        }
        return userOpt.get().getReviews().stream().map(r -> reviewConverter.toDto(r)).collect(Collectors.toList());
    }

/*
    public List<UserDto> deleteAllUsers(){
        return userRepository.
    }

    public List<UserDto> deleteUserById(int id){
        if(id < 0) {
            LOGGER.log(Level.WARN, "Unknown user: " + id);
            throw new InvalidUserId(Integer.toString(id));
        }
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("");
        }
        return userOpt.stream().


    }*/

  //  public User createUser()


    // userService.getReviewByMovieTitle(id, title); - Falta!
    //userService.getUserReviews(id) - OK!
    // userService.createUser(user)
    //userService.updateUser(user)
    // userService.deleteUserById(id)
    // userService.deleteAllUsers()

}
