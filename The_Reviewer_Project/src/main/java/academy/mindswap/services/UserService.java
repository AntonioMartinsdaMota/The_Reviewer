package academy.mindswap.services;

import academy.mindswap.commands.ReviewDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.converters.MovieConverter;
import academy.mindswap.converters.ReviewConverter;
import academy.mindswap.converters.UserConverter;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.UserRepository;
import academy.mindswap.persistence.repositories.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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

    public Optional<UserDto> getUserById(int id) throws UserNotFoundException {
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
        return userRepository.findAll().stream().map(userconverter::toDto).collect(Collectors.toList());
    }



    public List<ReviewDto> getReviewsByUserId(int id) throws UserNotFoundException{
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

    public UserDto createUser(UserDto userDto) throws UserAlreadyExistsException {
        if(userRepository.findByName(userDto.getUsername()).isPresent() || userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(userDto.getUsername());
        }
        return userconverter.toDto(userRepository.save(userconverter.toEntity(userDto)));

    }

    public UserDto updateUser(UserDto userDto) throws UserNotFoundException {

        //Get ID from Cookie

        Optional<User> userOpt = userRepository.findById(userDto.getId());

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException();
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
