package academy.mindswap.unit;

import academy.mindswap.commands.ReviewDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.converters.ReviewConverter;
import academy.mindswap.converters.UserConverter;
import academy.mindswap.exceptions.badRequestExceptions.InvalidAssertAuthoritiesException;
import academy.mindswap.mockdata.MockedData;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.RoleRepository;
import academy.mindswap.persistence.repositories.UserRepository;
import academy.mindswap.services.ReviewService;
import academy.mindswap.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserConverter userConverter;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ReviewConverter reviewConverter;

    @BeforeEach
    public void setUp() {

        modelMapper = new ModelMapper();
        userConverter = new UserConverter(modelMapper);
        userService = new UserService(userRepository, userConverter,reviewConverter,roleRepository,passwordEncoder);

    }

    @Test
    public void test_getUserById_Should_Return_Success() {
        //Given
        User user = MockedData.getMockedUser();

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
//        when(userConverter.convertToDto(user)).thenReturn(MockedData.getMockedUserDto(user));
//        when(modelMapper.map(user, UserDto.class)).thenReturn(MockedData.getMockedUserDto(user));

        //When
        UserDto response = userService.getUserById(user.getUserId()).get();
        UserDto expected = MockedData.getMockedUserDto(user);

        //Then
        assertEquals(expected.toString(), response.toString());

        // assertThat(response, samePropertyValuesAs(expected));


    }

    @Test
    public void test_getAllUsers_Should_Return_Success() {
        //Given
        List<User> userList = MockedData.getMockedUsers();
        when(userRepository.findAll()).thenReturn(userList);

        //When
        List<UserDto> response = userService.getAllUsers();
        List<UserDto> expected = MockedData.getMockedUsersDto(userList);

        //Then
        assertEquals(expected.toString(), response.toString());


    }

    @Test
    public void test_getUserByEmail_Should_Return_Success() {
        //Given
        User user = MockedData.getMockedUser();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));


        //When
        User response = userService.getUserByEmail(user.getEmail()).get();
        User expected = userConverter.toEntity(MockedData.getMockedUserDto(user));

        //Then
        assertEquals(expected.toString(), response.toString());

    }

    @Test
    public void test_turnAdmin_should_return_InvalidAssertAuthoritiesException() {
        //Given
        User user = MockedData.getMockedUser();
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(roleRepository.findByRole(user.getRoles().iterator().next().getRole())).thenReturn((user.getRoles().iterator().next()));

        //Then
        assertThrows(InvalidAssertAuthoritiesException.class, () -> userService.turnAdmin(user.getUserId()));


    }

}