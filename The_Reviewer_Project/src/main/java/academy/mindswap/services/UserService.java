package academy.mindswap.services;


import academy.mindswap.commands.ReviewDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.converters.MovieConverter;
import academy.mindswap.converters.ReviewConverter;
import academy.mindswap.converters.UserConverter;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.UserRepository;
import academy.mindswap.exceptions.badRequestExceptions.*;
import academy.mindswap.exceptions.notFoundExceptions.*;
import academy.mindswap.exceptions.otherExceptions.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);


    private final UserRepository userRepository;


    private final UserConverter userconverter;

    @Autowired
    private ReviewConverter reviewConverter;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieConverter;

    @Autowired
    private CookiesService cookiesService;


    public User login(String name, String password) {
        return userRepository.findByUsernameAndPassword(name, password);
    }

    public User validate(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User findByEmailAndPassword(String email, String password){
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if(user.isEmpty()){
            throw new LoginRequestFailedException();
        }
        return user.get();
    }


    public Optional<UserDto> getUserById(int id) {
        if(id < 0) {
            //LOGGER.log(Level.WARN, "Unknown user: " + id);
            throw new InvalidUserIdException(Integer.toString(id));
        }
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new UserNotFoundException(Integer.toString(id));
        }
        return user.map(userconverter::toDto);
    }

    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();

        for (User u:users) {
            usersDto.add(userconverter.toDto(u));
        }
        return usersDto;
    }



    public List<ReviewDto> getReviewsByUserId(int id){
        if(id < 0) {
            //LOGGER.log(Level.WARN, "Unknown user: " + id);
            throw new InvalidUserIdException(Integer.toString(id));
        }
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(id));
        }
        return userOpt.get().getReviews().stream().map(r -> reviewConverter.convertToDto(r)).collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto){
        if(userRepository.findByUsername(userDto.getUsername()).isPresent() || userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if(userDto.getPassword().length() < 7){
            throw new InvalidPasswordException();
        }
        return userconverter.toDto(userRepository.save(userconverter.toEntity(userDto)));

    }

    public UserDto updateUser(UserDto userDto, HttpServletRequest request) {

        Integer userId = cookiesService.getIdFromCookie(request);

        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(userId));
        }

        User user = userOpt.get();

        if(userDto.getUsername() == null) {
            userDto.setUsername(user.getUsername());
        }

        if(userDto.getEmail() == null) {
            userDto.setEmail(user.getEmail());
        }

        return userconverter.toDto(userRepository.save(userconverter.toEntity(userDto)));
    }

    public void deleteUserByUserId(int id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
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
