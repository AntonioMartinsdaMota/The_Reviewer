package academy.mindswap.services;

import academy.mindswap.commands.ReviewDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.converters.MovieConverter;
import academy.mindswap.converters.ReviewConverter;
import academy.mindswap.converters.UserConverter;
import academy.mindswap.persistence.models.Role;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.ReviewRepository;
import academy.mindswap.persistence.repositories.RoleRepository;
import academy.mindswap.persistence.repositories.UserRepository;
import academy.mindswap.exceptions.badRequestExceptions.*;
import academy.mindswap.exceptions.notFoundExceptions.*;
import academy.mindswap.exceptions.otherExceptions.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    //@Autowired
    private final UserRepository userRepository;

    //@Autowired
    private final UserConverter userconverter;


    private final ReviewConverter reviewConverter;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieConverter;

    @Autowired
    private TokenService tokenService;


    private final RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    public User login(String name, String password) {
        return userRepository.findByUsernameAndPassword(name, password);
    }

    public User validate(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User findByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isEmpty()) {
            throw new LoginRequestFailedException();
        }
        return user.get();
    }


    public Optional<UserDto> getUserById(int id) {
        if (id < 0) {
            //LOGGER.log(Level.WARN, "Unknown user: " + id);
            throw new InvalidUserIdException(Integer.toString(id));
        }
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException(Integer.toString(id));
        }
        return user.map(userconverter::toDto);
    }

    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();

        for (User u : users) {
            usersDto.add(userconverter.toDto(u));
        }
        return usersDto;
    }


    public List<ReviewDto> getReviewsByUserId(int id) {
        if (id < 0) {
            //LOGGER.log(Level.WARN, "Unknown user: " + id);
            throw new InvalidUserIdException(Integer.toString(id));
        }
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(id));
        }
        return userOpt.get().getReviews().stream().map(r -> reviewConverter.convertToDto(r)).collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent() || userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (userDto.getPassword().length() < 7 || userDto.getPassword() == null) {
            throw new InvalidPasswordException();
        }

        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encryptedPassword);
        User user = userconverter.toEntity(userDto);
        user.setRoles(List.of(roleRepository.findByRole("USER")));
        return userconverter.toDto(userRepository.save(user));

    }

    public UserDto updateUser(UserDto userDto, HttpServletRequest request) {

        String userEmail = tokenService.getEmailFromToken(request);

        Optional<User> userOpt = userRepository.findByEmail(userEmail);

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException(userEmail);
        }

        User user = userOpt.get();

        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }

        return userconverter.toDto(userRepository.save(user));
    }

    public void deleteUserByUserId(int id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void createRoles() {
        List<Role> roles = roleRepository.findAll();

        if (roles.isEmpty()) {
            roleRepository.save(new Role(null, "OWNER"));
            roleRepository.save(new Role(null, "ADMIN"));
            roleRepository.save(new Role(null, "USER"));
        }
    }

    public void createOwner() {
        Optional<User> user = userRepository.findById(1);

        if (user.isEmpty()) {
            User newUser =
                    User.builder()
                            .userId(null)
                            .username("owner")
                            .email("owner@owner")
                            .roles(new ArrayList<>())
                            .password(passwordEncoder.encode("owner123"))
                            .build();

            newUser.getRoles().add(roleRepository.findByRole("OWNER"));

            userRepository.save(newUser);
        }
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserDto turnAdmin(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException(userId.toString());
        }
        User user = userOpt.get();

        if (user.getRoles().contains(roleRepository.findByRole("ADMIN")) ||
                user.getRoles().contains(roleRepository.findByRole("OWNER"))) {
            throw new InvalidAssertAuthoritiesException();
        }

        user.getRoles().add(roleRepository.findByRole("ADMIN"));
        return userconverter.toDto(userRepository.save(user));
    }

}
