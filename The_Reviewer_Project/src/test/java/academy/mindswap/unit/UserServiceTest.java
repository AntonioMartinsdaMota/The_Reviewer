package academy.mindswap.unit;

import academy.mindswap.commands.UserDto;
import academy.mindswap.converters.UserConverter;
import academy.mindswap.mockdata.MockedData;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.UserRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {

        modelMapper = new ModelMapper();
        userConverter = new UserConverter(modelMapper);
        userService = new UserService(userRepository, userConverter);

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
        assertEquals(expected, response);

        //assertThat(response, samePropertyValuesAs(expected));


    }

}
