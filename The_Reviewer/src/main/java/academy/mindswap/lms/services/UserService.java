package academy.mindswap.lms.services;

import academy.mindswap.lms.annotations.MindswapAnnotation;
import academy.mindswap.lms.commands.UserDto;
import academy.mindswap.lms.converters.UserConverter;
import academy.mindswap.lms.exceptions.InvalidUserId;
import academy.mindswap.lms.exceptions.UserNotFoundException;
import academy.mindswap.lms.persistence.models.User;
import academy.mindswap.lms.persistence.repositories.UserRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    public List<UserDto> getUserByName(String name) {
        LOGGER.log(Level.INFO, "getUserByName: " + name);
        List<User> users = userRepository.findByName(name);

        return users.stream()
                .map(userConverter::toDto)
                .collect(Collectors.toList());
    }




    public List<UserDto> getUserByOther(String name) {
        return userRepository.findByOtherNameThatIWant(name)
                .stream()
                .map(userConverter::toDto).collect(Collectors.toList());
    }

    public UserDto save(UserDto userDto) {
        return userConverter
                .toDto(
                        userRepository.save(
                        userConverter.toEntity(userDto)
                ));
    }

    public User login(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }

    @MindswapAnnotation
    public List<UserDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userConverter::toDto).collect(Collectors.toList());
    }


    public Optional<UserDto> getUserById(int id) {

        if(id < 0) {
            LOGGER.log(Level.WARN, "Users are trying to break our site: " + id);
            throw new InvalidUserId(Integer.toString(id));
        }
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new UserNotFoundException(Integer.toString(id));
        }
        return user.map(userConverter::toDto);
    }

    public User validate(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(()->new UserNotFoundException(email));
    }
}
